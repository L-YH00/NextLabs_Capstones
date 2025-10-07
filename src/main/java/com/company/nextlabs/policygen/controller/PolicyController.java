package com.company.nextlabs.policygen.controller;

import com.company.nextlabs.policygen.exception.ExcelProcessingException;
import com.company.nextlabs.policygen.service.PolicyGenerationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
public class PolicyController {

    private final PolicyGenerationService policyService;

    public PolicyController(PolicyGenerationService policyService) {
        this.policyService = policyService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }

        try {
            // Convert MultipartFile to File
            File file = File.createTempFile("upload-", ".xlsx");
            multipartFile.transferTo(file);

            // Pass to service
            policyService.processExcelFile(file);

            return ResponseEntity.ok("Excel processed successfully!");

        } catch (IOException | ExcelProcessingException e) {
            return ResponseEntity.status(500).body("Failed to process file: " + e.getMessage());
        }
    }
}