package com.company.nextlabs.policygen.nextlabs;

import com.company.nextlabs.policygen.config.NextLabsConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * AuthenticationManager: requests OIDC token from NextLabs.
 *
 * - Uses app.nextlabs.oidc.insecure_ssl flag to control whether SSL hostname
 *   verification / certificate checking is bypassed. (DEV only)
 */
@Component
public class AuthenticationManager {

    private final NextLabsConfig nextLabsConfig;
    private final ObjectMapper mapper = new ObjectMapper();

    public AuthenticationManager(NextLabsConfig nextLabsConfig) {
        this.nextLabsConfig = nextLabsConfig;
    }

    /**
     * Request an access token using password grant (x-www-form-urlencoded POST).
     *
     * @return access token string
     * @throws IOException on HTTP or parsing errors
     */
    public String getAccessToken() throws IOException {
        OkHttpClient client = buildClient();

        // Prepare form body (grant_type, username, password, client_id)
        FormBody formBody = new FormBody.Builder()
                .add("grant_type", safeGet(nextLabsConfig.getOidc().getGrantType(), "password"))
                .add("username", Objects.requireNonNull(nextLabsConfig.getUsername()))
                .add("password", Objects.requireNonNull(nextLabsConfig.getPassword()))
                .add("client_id", safeGet(nextLabsConfig.getOidc().getClientId(), "ControlCenterOIDCClient"))
                .build();

        String tokenUrl = safeGet(nextLabsConfig.getOidc().getTokenUrl(), nextLabsConfig.getBaseUrl() + "/cas/oidc/accessToken");

        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(formBody)
                .header("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new IOException("Empty response body from auth server");
            }
            String body = response.body().string();

            if (!response.isSuccessful()) {
                throw new IOException("Auth server returned HTTP " + response.code() + ": " + body);
            }

            JsonNode root = mapper.readTree(body);
            if (root.has("access_token")) {
                return root.get("access_token").asText();
            } else {
                throw new IOException("No access_token in response: " + body);
            }
        }
    }

    private OkHttpClient buildClient() {
        if (nextLabsConfig.getOidc().isInsecureSsl()) {
            return buildUnsafeClient();
        } else {
            return new OkHttpClient.Builder().build();
        }
    }

    /**
     * Unsafe OkHttp client for development: trusts all certs and disables hostname verification.
     * DO NOT USE IN PRODUCTION.
     */
    private OkHttpClient buildUnsafeClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                        @Override public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                        @Override public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        } catch (Exception e) {
            // fallback to default client (shouldn't usually happen)
            return new OkHttpClient.Builder().build();
        }
    }

    private static String safeGet(String value, String fallback) {
        return (value == null || value.isBlank()) ? fallback : value;
    }
}
