@API
Feature: API Test

  Scenario: Get all products
    Given I request all products
    Then I get a status code of 200