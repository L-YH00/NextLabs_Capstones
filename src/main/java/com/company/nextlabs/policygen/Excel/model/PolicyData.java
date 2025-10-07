package com.company.nextlabs.policygen.Excel.model;

public class PolicyData {

    private String policyId;
    private String policyName;
    private String Description;
    private String PolicyType;
    private String status;
    private String effectiveFrom;
    private String effectiveTo;

    // Getters and Setters
    @Override
    public String toString() {
        return "PolicyData{" +
                "policyId='" + policyId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", description='" + Description + '\'' +
                ", policyType='" + PolicyType + '\'' +
                ", status='" + status + '\'' +
                ", effectiveFrom='" + effectiveFrom + '\'' +
                ", effectiveTo='" + effectiveTo + '\'' +
                '}';
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setPolicyType(String policyType) {
        this.PolicyType = policyType;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public void setEffectiveTo(String effectiveTo) {
        this.effectiveTo = effectiveTo;
    }
}
