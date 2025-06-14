Feature: Retrieve User Details by Id

  Scenario Outline: Successful retieval of User Details by ID <userId>
    Given the API end point is "api/users/<userId>"
    When I send a GET request
    Then the response status code should be 200
    And the respose body is displaying the following details:
      | id | email | first_name | last_name | avatar | url | text |

    Examples:
      | userId |
      |      2 |
