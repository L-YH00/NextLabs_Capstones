package com.company.nextlabs.policygen.Excel;

import com.company.nextlabs.policygen.exception.ExcelProcessingException;
import com.company.nextlabs.policygen.exception.ValidationException;
import com.company.nextlabs.policygen.Excel.model.PolicyData;
import com.company.nextlabs.policygen.Excel.model.RuleData;
import com.company.nextlabs.policygen.Excel.model.AttributeData;

import java.io.File;
import java.util.List;

public interface ExcelReader {
    List<PolicyData> readPolicies(File excelFile) throws ExcelProcessingException;
    List<RuleData> readRules(File excelFile) throws ExcelProcessingException;
    List<AttributeData> readAttributes(File excelFile) throws ExcelProcessingException;
    void validateStructure(File excelFile) throws ValidationException;
}
