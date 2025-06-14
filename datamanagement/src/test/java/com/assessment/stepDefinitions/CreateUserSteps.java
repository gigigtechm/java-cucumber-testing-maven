package com.assessment.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.util.Map;

import com.assessment.utils.ConfigReader;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CreateUserSteps {

    private static String baseUrl;
    private static String apiEndPoint;
    private Response response;

    @Before
    public void setUpUrl() {
        baseUrl = ConfigReader.getProperty("baseUrl");
    }

    @Given("the API end point is {string}")
    public void setApiEndPoint(String endpoint) {
        apiEndPoint = baseUrl + endpoint;
    }

    @When("I send a POST request with the following data")
    public void sendPostRequest(Map<String, String> data) {
        response = given().contentType("application/json").body(data).when().post().then().extract().response();
    }

    @Then("the response status code should be {int}")
    public void validateResponseStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("the respose body is displaying the following details")
    public void validateResponseBody(Map<String, String> expectedData) {

        // Validate Name and Job
        Map<String, Object> responseData = response.jsonPath().getMap("");
        for (String key : expectedData.keySet()) {
            assertEquals(expectedData.get(key), responseData.get(key).toString());
        }
        // Validate userid is not null
        int actualUserId = response.jsonPath().getInt("id");
        assertTrue(actualUserId > 0);
        // Validate the timestamp
        String actualTimestamp = response.jsonPath().getString("createdAt");
        String expTimestampRegex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z";
        assertTrue(actualTimestamp.matches(expTimestampRegex));

    }

}
