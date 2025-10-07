package com.company.nextlabs.policygen.excel;

import com.company.nextlabs.policygen.Excel.ExcelReaderImpl;
import com.company.nextlabs.policygen.config.ExcelConfig;
import com.company.nextlabs.policygen.exception.ExcelProcessingException;
import com.company.nextlabs.policygen.exception.ValidationException;
import com.company.nextlabs.policygen.Excel.model.AttributeData;
import com.company.nextlabs.policygen.Excel.model.PolicyData;
import com.company.nextlabs.policygen.Excel.model.RuleData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExcelReaderImplTest {

    public static void main(String[] args) {
        //Create ExcelConfig manually
        ExcelConfig config = new ExcelConfig();
        config.setTemplateValidation(true);
        config.setSupportedFormats(List.of("xlsx", "xls"));
        config.setTemplatesPath("src/test/resources/test-data/");

        //Create ExcelReaderImpl
        ExcelReaderImpl reader = new ExcelReaderImpl(config);

        //Load test Excel file
        File testFile = new File("src/test/resources/test-data/valid-policies.xlsx");

        try {
            //Validate structure
            reader.validateStructure(testFile);
            System.out.println("Excel structure validation passed.");

            //Read policies
            List<PolicyData> policies = reader.readPolicies(testFile);
            System.out.println("Policies read:");
            policies.forEach(System.out::println);

            //Read rules
            List<RuleData> rules = reader.readRules(testFile);
            System.out.println("Rules read:");
            rules.forEach(System.out::println);

            //Read attributes
            List<AttributeData> attributes = reader.readAttributes(testFile);
            System.out.println("Attributes read:");
            attributes.forEach(System.out::println);

        } catch (ExcelProcessingException | ValidationException e) {
            e.printStackTrace();
            System.err.println("Error reading Excel: " + e.getMessage());
        }
    }
}
