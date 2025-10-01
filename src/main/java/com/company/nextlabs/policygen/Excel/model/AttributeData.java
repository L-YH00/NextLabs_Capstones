package com.company.nextlabs.policygen.Excel.model;

public class AttributeData {

    private String AttributeName;
    private String AttributeType;
    private String DataType;
    private String PossibleValues;
    private String Description;
    public String Required;

    // Getters and Setters
    public String getAttributeName() {
        return AttributeName;
    }

    public void setAttributeName(String attributeName) {
        this.AttributeName = attributeName;
    }

    public String getAttributeType() {
        return AttributeType;
    }

    public void setAttributeType(String attributeType) {
        this.AttributeType = attributeType;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        this.DataType = dataType;
    }

    public String getPossibleValues() {
        return PossibleValues;
    }

    public void setPossibleValues(String possibleValues) {
        this.PossibleValues = possibleValues;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getRequired() {
        return Required;
    }

    public void setRequired(String required) {
        this.Required = required;
    }


}
