package com.company.nextlabs.policygen.service;

import com.company.nextlabs.policygen.Excel.ExcelReader;
import com.company.nextlabs.policygen.Excel.model.AttributeData;
import com.company.nextlabs.policygen.Excel.model.PolicyData;
import com.company.nextlabs.policygen.Excel.model.RuleData;
import com.company.nextlabs.policygen.exception.ExcelProcessingException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PolicyGenerationService {
    private final ExcelReader excelReader;

    public PolicyGenerationService(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }

    public void processExcelFile(File filePath) throws ExcelProcessingException {
        List<PolicyData> policies = excelReader.readPolicies(filePath);
        List<RuleData> rules = excelReader.readRules(filePath);
        List<AttributeData> attributes = excelReader.readAttributes(filePath);

        // Next step: use these to create policies via API
        System.out.println("Policies loaded: " + policies.size());
        System.out.println("Rules loaded: " + rules.size());
        System.out.println("Attributes loaded: " + attributes.size());
    }
}
