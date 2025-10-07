package com.company.nextlabs.policygen.Excel.model;

public class RuleData {

    private String PolicyID;
    private String RuleType;
    private String AttributeName;
    private String Operator;
    private String Value;
    private String LogicalOperator;

    //Getters and Setters
    @Override
    public String toString() {
        return "RuleData{" +
                "policyID='" + PolicyID + '\'' +
                ", ruleType='" + RuleType + '\'' +
                ", attributeName='" + AttributeName + '\'' +
                ", operator='" + Operator + '\'' +
                ", value='" + Value + '\'' +
                ", logicalOperator='" + LogicalOperator + '\'' +
                '}';
    }

    public void setPolicyID(String policyID) {
        this.PolicyID = policyID;
    }

    public void setRuleType(String ruleType) {
        this.RuleType = ruleType;
    }

    public void setAttributeName(String attributeName) {
        this.AttributeName = attributeName;
    }

    public void setOperator(String operator) {
        this.Operator = operator;
    }

    public void setValue(String value) {
        this.Value = value;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.LogicalOperator = logicalOperator;
    }
}
