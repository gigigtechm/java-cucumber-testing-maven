package com.assessment.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",  // Path to feature files
    glue = "com.assessment.stepDefinitions",  // Path to step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html"},  // Reports & logs
    monochrome = true  // Cleaner console output
)

public class TestRunner {
}
