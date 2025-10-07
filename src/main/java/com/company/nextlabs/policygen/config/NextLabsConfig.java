package com.company.nextlabs.policygen.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.nextlabs")
public class NextLabsConfig {

    private String baseUrl;
    private String username;
    private String password;
    private int timeout = 30;
    private int retryAttempts = 3;
    private String authType;

    private Oidc oidc = new Oidc();

    // --- Nested OIDC config ---
    public static class Oidc {
        private String tokenUrl;
        private String grantType;
        private String clientId;
        private boolean insecureSsl;

        public String getTokenUrl() {
            return tokenUrl;
        }

        public void setTokenUrl(String tokenUrl) {
            this.tokenUrl = tokenUrl;
        }

        public String getGrantType() {
            return grantType;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public boolean isInsecureSsl() {
            return insecureSsl;
        }

        public void setInsecureSsl(boolean insecureSsl) {
            this.insecureSsl = insecureSsl;
        }
    }
    // Getters and setters
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public String getAuthType() { return authType; }

    public void setAuthType(String authType) { this.authType = authType; }

    public Oidc getOidc() { return oidc; }

    public void setOidc(Oidc oidc) { this.oidc = oidc; }

}