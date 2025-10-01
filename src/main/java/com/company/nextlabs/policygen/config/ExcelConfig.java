package com.company.nextlabs.policygen.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.excel")
public class ExcelConfig {
    private boolean templateValidation;
    private List<String> supportedFormats;
    private String maxFileSize;
    private String templatesPath;

    // Getters and setters
    public boolean isTemplateValidation() {
        return templateValidation;
    }

    public void setTemplateValidation(boolean templateValidation) {
        this.templateValidation = templateValidation;
    }

    public List<String> getSupportedFormats() {
        return supportedFormats;
    }

    public void setSupportedFormats(List<String> supportedFormats) {
        this.supportedFormats = supportedFormats;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getTemplatesPath() {
        return templatesPath;
    }

    public void setTemplatesPath(String templatesPath) {
        this.templatesPath = templatesPath;
    }
}