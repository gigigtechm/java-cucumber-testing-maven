Feature: Home Page

  Scenario: Verify Home Page
    Given User is on the Home Page
    Then User scrolls to API lists section
    And Check that the list item 'GET LIST USERS' is selected by default
    Then Click on the 'DELETE' list item
