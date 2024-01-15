Feature: User Credit Management

  Scenario: Adding credit to the user
    Given the user has a credit of 100.0
    When the user adds 50.0 credit
    Then the user's credit should be 150.0

  Scenario: Invalid Credit Range Exception
    Given the user has a credit of 100.0
    When an attempt is made to add credit of -50.0
    Then an InvalidCreditRange exception should be thrown

  Scenario: Withdrawing Sufficient Credit from the User
    Given the user has a credit of 100.0
    When the user withdraws 30.0 credit
    Then the user should have a remained credit of 70.0

  Scenario: Withdrawing Insufficient Credit from the User
    Given the user has a credit of 100.0
    When the user withdraws more than it's credit like 150.0
    Then an InsufficientCredit exception should be thrown