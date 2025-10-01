package com.company.nextlabs.policygen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {

    private NextLabsConfig nextlabs;
    private ExcelConfig excel;

    // Getters and setters

    public NextLabsConfig getNextlabs() {
        return nextlabs;
    }

    public void setNextlabs(NextLabsConfig nextlabs) {
        this.nextlabs = nextlabs;
    }

    public ExcelConfig getExcel() {
        return excel;
    }

    public void setExcel(ExcelConfig excel) {
        this.excel = excel;
    }
}