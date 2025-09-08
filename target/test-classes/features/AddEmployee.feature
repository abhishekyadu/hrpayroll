Feature: Add the Employee

Scenario: Positive Testcase
    Given I launch the application with "correct"
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the dashboard

    And I navigate to Add Employee page
    When I enter employee details from property file
    And I click on Save for employee
    Then I should see employee success message "Successfully Saved"
    
Scenario: Negative Testcase - Missing Required Fields
    Given I am logged out
    When I login with username "Admin" and password "admin123"
    Then I should be redirected to the dashboard
    And I navigate to Add Employee page
    When I enter incomplete employee details
        | firstName | lastName | empid |
        |           | Doe      | 123   |
    And I click on Save for employee
    Then I should see employee error message "Required"
