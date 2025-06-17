package com.assessment.stepDefinitions;

import com.assessment.utils.ConfigReader;
import com.assessment.utils.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageSteps {

    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(HomePageSteps.class);

    public HomePageSteps() {
        this.driver = DriverManager.getDriver();
        logger.info("WebDriver instance created.");
    }

    @Given("User is on the Home Page")
    public void validateHomePage() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        logger.info("Navigating to base URL: {}", baseUrl);
        driver.get(baseUrl);
    }

    @Then("User scrolls to API lists section")
    public void validateApiListSection() {
        logger.info("Scrolling to API list section...");
        WebElement apiListSection = driver.findElement(By.id("console"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", apiListSection);
        assertTrue(apiListSection.isDisplayed(), " Api list section should be visible");
        logger.info("API list section is visible.");
    }

    @And("Check that the list item 'GET LIST USERS' is selected by default")
    public void validateDefaultSelection() {
        WebElement selectedListItem = driver.findElement(By.cssSelector("[data-id=\"users\"]"));
        assertTrue(selectedListItem.getAttribute("class").contains("active"));
        String selectedItemText = selectedListItem.getText();
        String expectedSelectedText = "List users";
        assertEquals(expectedSelectedText, selectedItemText);
        logger.info("Default selected list item validated: {}", selectedItemText);
    }

    @Then("Click on the 'DELETE' list item")
    public void clickOnDeleteItem() {
        logger.info("Clicking on the 'DELETE' list item...");
        WebElement deleteListItem = driver.findElement(By.cssSelector("[data-id=\"delete\"]"));
        deleteListItem.click();
    }

}
