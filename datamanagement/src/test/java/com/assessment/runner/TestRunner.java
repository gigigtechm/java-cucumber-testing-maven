package com.assessment.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.assessment.utils.DriverManager;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.assessment.stepDefinitions", plugin = { "pretty",
                "html:target/cucumber-reports.html" }, monochrome = true)

public class TestRunner {
        @BeforeClass
        public void setup() {
                DriverManager.getDriver();
        }

        @AfterClass
        public void tearDown() {
                DriverManager.quitDriver();
        }
}
