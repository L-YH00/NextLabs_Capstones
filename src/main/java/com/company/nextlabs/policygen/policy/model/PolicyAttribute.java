package com.company.nextlabs.policygen.policy.model;

/**
 * Represents a single attribute in a policy rule.
 * Maps to a condition in a Component.
 */
public class PolicyAttribute {
    private String key;       // attribute name
    private String operator;  // e.g., '=', 'IN'
    private String value;     // value to match

    public PolicyAttribute(String key, String operator, String value) {
        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    // Getters
    public String getKey() { return key; }
    public String getOperator() { return operator; }
    public String getValue() { return value; }

    // Optional: setters if needed in future
    public void setKey(String key) { this.key = key; }
    public void setOperator(String operator) { this.operator = operator; }
    public void setValue(String value) { this.value = value; }
}