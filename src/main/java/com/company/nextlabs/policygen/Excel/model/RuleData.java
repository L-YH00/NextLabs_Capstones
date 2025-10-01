package com.company.nextlabs.policygen.Excel.model;

public class RuleData {

    private String PolicyID;
    private String RuleType;
    private String AttributeName;
    private String Operator;
    private String Value;
    private String LogicalOperator;

    //Getters and Setters
    public  String getPolicyID() {
        return PolicyID;
    }

    public void setPolicyID(String policyID) {
        this.PolicyID = policyID;
    }

    public String getRuleType() {
        return RuleType;
    }

    public void setRuleType(String ruleType) {
        this.RuleType = ruleType;
    }

    public String getAttributeName() {
        return AttributeName;
    }
    public void setAttributeName(String attributeName) {
        this.AttributeName = attributeName;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        this.Operator = operator;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        this.Value = value;
    }

    public String getLogicalOperator() {
        return LogicalOperator;
    }

    public void setLogicalOperator(String logicalOperator) {
        this.LogicalOperator = logicalOperator;
    }
}
