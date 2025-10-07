package com.company.nextlabs.policygen.Excel;

import com.company.nextlabs.policygen.Excel.model.AttributeData;
import com.company.nextlabs.policygen.Excel.model.PolicyData;
import com.company.nextlabs.policygen.Excel.model.RuleData;
import com.company.nextlabs.policygen.exception.ExcelProcessingException;
import com.company.nextlabs.policygen.config.ExcelConfig;
import com.company.nextlabs.policygen.exception.ValidationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelReaderImpl implements ExcelReader {

    private final ExcelConfig excelConfig;

    public ExcelReaderImpl(ExcelConfig excelConfig) {
        this.excelConfig = excelConfig;
    }

    @Override
    public List<PolicyData> readPolicies(File excelFile) throws ExcelProcessingException {
        List<PolicyData> policies = new ArrayList<>();
        try (InputStream is = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheet("Policies");
            if (sheet == null) {
                throw new ExcelProcessingException("Policies sheet not found");
            }

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                PolicyData policy = new PolicyData();
                policy.setPolicyId(getCellValue(row.getCell(0)));
                policy.setPolicyName(getCellValue(row.getCell(1)));
                policy.setDescription(getCellValue(row.getCell(2)));
                policy.setPolicyType(getCellValue(row.getCell(3)));
                policy.setStatus(getCellValue(row.getCell(4)));
                policy.setEffectiveFrom(getCellValue(row.getCell(5)));
                policy.setEffectiveTo(getCellValue(row.getCell(6)));
                policies.add(policy);
            }

        } catch (Exception e) {
            throw new ExcelProcessingException("Failed to read Policies sheet: " + e.getMessage(), e);
        }
        return policies;
    }

    @Override
    public List<RuleData> readRules(File excelFile) throws ExcelProcessingException {
        List<RuleData> rules = new ArrayList<>();

        try (InputStream is = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheet("Rules");
            if (sheet == null) {
                throw new ExcelProcessingException("Rules sheet not found");
            }

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                RuleData rule = new RuleData();

                rule.setPolicyID(getCellValue(row.getCell(0)));
                rule.setRuleType(getCellValue(row.getCell(1)));
                rule.setAttributeName(getCellValue(row.getCell(2)));
                rule.setOperator(getCellValue(row.getCell(3)));
                rule.setValue(getCellValue(row.getCell(4)));
                rule.setLogicalOperator(getCellValue(row.getCell(5)));

                rules.add(rule);
            }

        } catch (Exception e) {
            throw new ExcelProcessingException("Failed to read Rules sheet: " + e.getMessage(), e);
        }

        return rules;
    }


    @Override
    public List<AttributeData> readAttributes(File excelFile) throws ExcelProcessingException {
        List<AttributeData> attributes = new ArrayList<>();

        try (InputStream is = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheet("Attributes");
            if (sheet == null) {
                throw new ExcelProcessingException("Attributes sheet not found");
            }

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                AttributeData attribute = new AttributeData();

                attribute.setAttributeName(getCellValue(row.getCell(0)));
                attribute.setAttributeType(getCellValue(row.getCell(1)));
                attribute.setDataType(getCellValue(row.getCell(2)));
                attribute.setPossibleValues(getCellValue(row.getCell(3)));
                attribute.setDescription(getCellValue(row.getCell(4)));
                attribute.setRequired(getCellValue(row.getCell(5)));

                attributes.add(attribute);
            }

        } catch (Exception e) {
            throw new ExcelProcessingException("Failed to read Attributes sheet: " + e.getMessage(), e);
        }

        return attributes;
    }

    @Override
    public void validateStructure(File excelFile) throws ValidationException {
        try (InputStream is = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(is)) {

            // Expected sheets
            String[] expectedSheets = {"Policies", "Rules", "Attributes", "Metadata"};

            for (String sheetName : expectedSheets) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new ValidationException("Missing sheet: " + sheetName);
                }
            }

            // Optional: validate columns for Policies sheet
//            Sheet policiesSheet = workbook.getSheet("Policies");
//            Row policiesHeader = policiesSheet.getRow(0);
//            String[] expectedPolicyColumns = {"PolicyID", "PolicyName", "Description", "PolicyType", "Status", "EffectiveFrom", "EffectiveTo"};
//            for (int i = 0; i < expectedPolicyColumns.length; i++) {
//                String headerName = getCellValue(policiesHeader.getCell(i));
//                if (!expectedPolicyColumns[i].equalsIgnoreCase(headerName)) {
//                    throw new ValidationException("Policies sheet column mismatch at index " + i + ": expected '" + expectedPolicyColumns[i] + "', found '" + headerName + "'");
//                }
//            }

        } catch (Exception e) {
            if (e instanceof ValidationException) {
                throw (ValidationException) e;
            }
            throw new ValidationException("Failed to validate Excel structure: " + e.getMessage(), e);
        }
    }

    // Helper to get cell value as String
    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default: return null;
        }
    }
}
