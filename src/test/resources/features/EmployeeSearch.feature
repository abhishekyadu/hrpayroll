
Feature: Search the Employee


Scenario: Positive Case- Search employee by ID
    Given I am logged out
    When I login with username "<username>" and password "<password>"
    And I navigate to PIM
    And I search for employee by id "1947"
    And I click on Search
    Then I should see "No Record Found"


Scenario: Negative Case- Search employee by ID
    Given I am logged out
    When I login with username "<username>" and password "<password>"
    And I navigate to PIM
    And I search for employee by id "0000000"
    And I click on Search
    Then I should see "No Records Found"
