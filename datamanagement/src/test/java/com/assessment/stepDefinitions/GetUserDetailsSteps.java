package com.assessment.stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.assessment.utils.ConfigReader;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.util.Map;

public class GetUserDetailsSteps {

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

  @When("I send a GET request")
  public void sendGetRequest() {
    response = given().get(apiEndPoint);
  }

  @Then("the response status code should be {int}")
  public void validateResponseStatusCode(int statusCode) {
    assertEquals(statusCode, response.getStatusCode());
  }

  @And("the respose body is displaying the following details:")
  public void validateResponseBody() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> expectedResponseValues = mapper
        .readValue(new File("src/test/resources/expectedGetRespData.json"), Map.class);
    expectedResponseValues.forEach((key, value) -> response.then().body(key, equalTo(value)));
  }

}
