package com.company.nextlabs.policygen.Excel.model;

public class AttributeData {

    private String AttributeName;
    private String AttributeType;
    private String DataType;
    private String PossibleValues;
    private String Description;
    public String Required;

    // Getters and Setters
    @Override
    public String toString() {
        return "AttributeData{" +
                "attributeName='" + AttributeName + '\'' +
                ", attributeType='" + AttributeType + '\'' +
                ", dataType='" + DataType + '\'' +
                ", possibleValues='" + PossibleValues + '\'' +
                ", description='" + Description + '\'' +
                ", required='" + Required + '\'' +
                '}';
    }

    public void setAttributeName(String attributeName) {
        this.AttributeName = attributeName;
    }


    public void setAttributeType(String attributeType) {
        this.AttributeType = attributeType;
    }

    public void setDataType(String dataType) {
        this.DataType = dataType;
    }

    public void setPossibleValues(String possibleValues) {
        this.PossibleValues = possibleValues;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setRequired(String required) {
        this.Required = required;
    }

}
