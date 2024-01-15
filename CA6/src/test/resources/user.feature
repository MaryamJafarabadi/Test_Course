Feature: User Credit Management

  Scenario: Adding credit to the user
    Given the user has a credit of 100.0
    When the user adds 50.0 credit
    Then the user's credit should be 150.0
