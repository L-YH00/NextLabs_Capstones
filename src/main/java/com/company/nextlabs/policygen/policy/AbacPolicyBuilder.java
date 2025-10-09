package com.company.nextlabs.policygen.policy;

import com.company.nextlabs.policygen.nextlabs.PapApiService;
import com.company.nextlabs.policygen.policy.model.AbacPolicy;
import com.company.nextlabs.policygen.policy.model.PolicyRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to build ABAC policies and components and upload them via PapApiService
 */
public class AbacPolicyBuilder {

    private final PapApiService papApiService;

    public AbacPolicyBuilder(PapApiService papApiService) {
        this.papApiService = papApiService;
    }

    /**
     * Build and upload a test policy along with its components
     */
    public void buildAndUploadTestPolicy() throws IOException {
        System.out.println("🚀 Starting ABAC policy creation test...");

        // Create Subject Component
        PolicyRule subjectRule = new PolicyRule("Finance Department", "SUBJECT", "182")
                .addAttribute("department", "=", "Finance");
        String subjectComponentId = papApiService.createComponent(subjectRule);

        // Create Action Component
        PolicyRule actionRule = new PolicyRule("View", "RESOURCE", "23278")
                .addAttribute("view", "=", "VIEW_TKTS");
        String actionComponentId = papApiService.createComponent(actionRule);

        // Create Resource Component
        PolicyRule resourceRule = new PolicyRule("Finance Records", "RESOURCE", "23278")
                .addAttribute("Category", "=", "Finance Security");
        String resourceComponentId = papApiService.createComponent(resourceRule);

        System.out.println("Components created: Subject=" + subjectComponentId +
                ", Action=" + actionComponentId +
                ", Resource=" + resourceComponentId);

        // Build Policy
        AbacPolicy policy = new AbacPolicy("Finance Policy", "allow")
                .setDescription("Allow Finance Department to view finance records")
                .addTag("19786") // Example tag ID
                .addRule(new PolicyRule("SubjectRule", "SUBJECT", subjectComponentId))
                .addRule(new PolicyRule("ActionRule", "ACTION", actionComponentId))
                .addRule(new PolicyRule("ResourceRule", "RESOURCE", resourceComponentId));

        String policyId = papApiService.createPolicy(policy);
        System.out.println("Policy created with ID: " + policyId);
    }
}
