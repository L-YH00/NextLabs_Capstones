package com.company.nextlabs.policygen.nextlabs;

import com.company.nextlabs.policygen.policy.model.AbacPolicy;
import com.company.nextlabs.policygen.policy.model.PolicyRule;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;

/**
 * Service for interacting with NextLabs PAP API.
 * Handles creating Components and Policies.
 */
public class PapApiService {

    private final OkHttpClient client;
    private final String baseUrl;
    private final String bearerToken;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PapApiService(String baseUrl, String bearerToken) {
        this.baseUrl = baseUrl;
        this.bearerToken = bearerToken;
        this.client = unsafeClient();
    }

    // Unsafe OkHttp client to bypass SSL verification
    private OkHttpClient unsafeClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() { return new java.security.cert.X509Certificate[]{}; }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a Component based on a PolicyRule.
     */
    public String createComponent(PolicyRule rule) throws IOException {
        ObjectNode json = objectMapper.createObjectNode();
        json.put("name", rule.getName());
        json.put("type", rule.getComponentType()); // ✅ use "type" (not "componentType")
        json.put("status", "DRAFT"); // ✅ required field for creation
        json.put("policyModelId", rule.getPolicyModelId());

        // Map attributes to conditions
        ArrayNode conditions = objectMapper.createArrayNode();
        rule.getAttributes().forEach(attr -> {
            ObjectNode cond = objectMapper.createObjectNode();
            cond.put("attribute", attr.getKey());
            cond.put("operator", attr.getOperator());
            cond.put("value", attr.getValue());
            conditions.add(cond);
        });
        json.set("conditions", conditions);

        String payload = objectMapper.writeValueAsString(json);
        System.out.println("Sending Component JSON: " + payload);
        String response = sendPostRequest("/component/mgmt/add", payload);
        return extractIdFromResponse(response);
    }
    /**
     * Creates a Policy based on AbacPolicy object.
     */
    public String createPolicy(AbacPolicy policy) throws IOException {
        ObjectNode json = objectMapper.createObjectNode();
        json.put("name", policy.getName());
        json.put("description", policy.getDescription());
        json.put("effectType", policy.getEffectType());
        json.put("status", "DRAFT");

        // Tags
        ArrayNode tagsNode = objectMapper.createArrayNode();
        policy.getTags().forEach(tag -> {
            ObjectNode t = objectMapper.createObjectNode();
            t.put("id", tag);
            tagsNode.add(t);
        });
        json.set("tags", tagsNode);

        // Map rules to component arrays
        ArrayNode subjectComponents = objectMapper.createArrayNode();
        ArrayNode actionComponents = objectMapper.createArrayNode();
        ArrayNode resourceComponents = objectMapper.createArrayNode();

        for (PolicyRule rule : policy.getRules()) {
            ObjectNode compNode = objectMapper.createObjectNode();
            compNode.put("operator", "IN");
            ArrayNode components = objectMapper.createArrayNode();
            ObjectNode c = objectMapper.createObjectNode();
            // For now assume rule.getPolicyModelId() is the component ID
            c.put("id", rule.getPolicyModelId());
            components.add(c);
            compNode.set("components", components);

            switch (rule.getComponentType()) {
                case "SUBJECT":
                    subjectComponents.add(compNode);
                    break;
                case "ACTION":
                    actionComponents.add(compNode);
                    break;
                case "RESOURCE":
                    resourceComponents.add(compNode);
                    break;
                default:
                    // Optional: handle unexpected types
                    System.err.println("⚠ Unknown component type: " + rule.getComponentType());
            }
        }

        json.set("subjectComponents", subjectComponents);
        json.set("actionComponents", actionComponents);
        json.set("fromResourceComponents", resourceComponents);

        String payload = objectMapper.writeValueAsString(json);
        System.out.println("Sending Policy JSON: " + payload);
        String response = sendPostRequest("/policy/mgmt/add", payload);
        return extractIdFromResponse(response);
    }

    private String sendPostRequest(String path, String jsonBody) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + path)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body() != null ? response.body().string() : "";
            System.out.println("🔍 Response (" + response.code() + "): " + responseBody);

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response (" + response.code() + "): " + responseBody);
            }

            return responseBody;
        }
    }

    private String extractIdFromResponse(String responseBody) throws IOException {
        JsonNode node = objectMapper.readTree(responseBody);
        // Check if response is successful
        if (node.has("statusCode") && node.get("statusCode").asText().equals("1000")) {
            // ID can be directly in "data" or nested inside an object
            JsonNode dataNode = node.get("data");
            if (dataNode != null) {
                if (dataNode.isIntegralNumber()) {
                    return dataNode.asText();
                } else if (dataNode.has("id")) {
                    return dataNode.get("id").asText();
                }
            }
        }
        throw new IOException("Response does not contain valid id: " + responseBody);
    }
}