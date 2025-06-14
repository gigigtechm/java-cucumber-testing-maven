Feature: Create User

  Scenario: Successful creation of User
    Given the API end point is "api/users/"
    When I send a POST request with the following data
      | name | morpheus |
      | Job  | leader   |
    Then the response status code should be 201
    And the respose body is displaying the following details
      | name | morpheus |
      | Job  | leader   |
