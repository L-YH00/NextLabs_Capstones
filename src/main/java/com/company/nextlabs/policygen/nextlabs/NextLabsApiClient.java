package com.company.nextlabs.policygen.nextlabs;

import com.company.nextlabs.policygen.exception.NextLabsApiException;
import com.company.nextlabs.policygen.nextlabs.dto.ApiResponse;
import com.company.nextlabs.policygen.policy.model.AbacPolicy;

import java.util.List;

public interface NextLabsApiClient {
    ApiResponse<String> createPolicy(AbacPolicy policy) throws NextLabsApiException;
    ApiResponse<String> updatePolicy(String policyId, AbacPolicy policy) throws NextLabsApiException;
    ApiResponse<Void> deletePolicy(String policyId) throws NextLabsApiException;
    ApiResponse<List<String>> listPolicies() throws NextLabsApiException;
    boolean authenticate() throws NextLabsApiException;
}