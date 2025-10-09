package com.company.nextlabs.policygen.policy.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents an ABAC Policy
 * Maps to a Policy in the PAP system.
 */
public class AbacPolicy {
    private String id;                        // Optional, assigned by PAP
    private String name;                      // Policy name
    private String description;               // Policy description
    private String effectType;                // "allow" or "deny"
    private List<PolicyRule> rules = new ArrayList<>(); // Subject/Resource/Action rules
    private List<String> tags = new ArrayList<>();      // Tags (IDs) for the policy

    public AbacPolicy(String name, String effectType) {
        this.name = name;
        this.effectType = effectType;
    }

    // Fluent setters
    public AbacPolicy setDescription(String description) {
        this.description = description;
        return this;
    }

    public AbacPolicy addRule(PolicyRule rule) {
        this.rules.add(rule);
        return this;
    }

    public AbacPolicy addTag(String tag) {
        this.tags.add(tag);
        return this;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getEffectType() { return effectType; }
    public List<PolicyRule> getRules() { return rules; }
    public List<String> getTags() { return tags; }

    // Optional setters for ID or tags if needed
    public void setId(String id) { this.id = id; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
