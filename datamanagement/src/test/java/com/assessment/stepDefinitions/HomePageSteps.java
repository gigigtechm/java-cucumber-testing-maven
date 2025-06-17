package com.assessment.stepDefinitions;

import com.assessment.utils.ConfigReader;
import com.assessment.utils.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageSteps {

    WebDriver driver;

    public HomePageSteps() {
        this.driver = DriverManager.getDriver();
    }

    @Given("User is on the Home Page")
    public void validateHomePage() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl);
    }

    @Then("User scrolls to API lists section")
    public void validateApiListSection() {
        WebElement apiListSection = driver.findElement(By.id("console"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", apiListSection);
        assertTrue(apiListSection.isDisplayed(), " Api list section should be visible");
    }

    @And("Check that the list item 'GET LIST USERS' is selected by default")
    public void validateDefaultSelection() {
        WebElement selectedListItem = driver.findElement(By.cssSelector("[data-id=\"users\"]"));
        assertTrue(selectedListItem.getAttribute("class").contains("active"));
        String selectedItemText = selectedListItem.getText();
        String expectedSelectedText = "List users";
        assertEquals(expectedSelectedText, selectedItemText);
    }

    @Then("Click on the 'DELETE' list item")
    public void clickOnDeleteItem() {
        WebElement deleteListItem = driver.findElement(By.cssSelector("[data-id=\"delete\"]"));
        deleteListItem.click();
    }

    @And("Check that the Request and Response of 'DELETE' list item is displayed")
    public void validateRequestAndResposneDelete() {

    }

}
