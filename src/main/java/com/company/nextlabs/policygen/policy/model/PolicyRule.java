package com.company.nextlabs.policygen.policy.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Component Rule for a policy.
 * Maps to a Component in the PAP system.
 */
public class PolicyRule {
    private String name;                 // Name of the rule/component
    private String componentType;        // "SUBJECT", "RESOURCE", "ACTION"
    private String policyModelId;        // ID of the policy model (component type)
    private List<PolicyAttribute> attributes = new ArrayList<>();  // Conditions

    public PolicyRule(String name, String componentType, String policyModelId) {
        this.name = name;
        this.componentType = componentType;
        this.policyModelId = policyModelId;
    }

    /**
     * Add an attribute (condition) to this rule
     */
    public PolicyRule addAttribute(String key, String operator, String value) {
        this.attributes.add(new PolicyAttribute(key, operator, value));
        return this;
    }

    // Getters
    public String getName() { return name; }
    public String getComponentType() { return componentType; }
    public String getPolicyModelId() { return policyModelId; }
    public List<PolicyAttribute> getAttributes() { return attributes; }

    // Optional: setters if you want to modify after creation
    public void setName(String name) { this.name = name; }
    public void setComponentType(String componentType) { this.componentType = componentType; }
    public void setPolicyModelId(String policyModelId) { this.policyModelId = policyModelId; }
    public void setAttributes(List<PolicyAttribute> attributes) { this.attributes = attributes; }
}