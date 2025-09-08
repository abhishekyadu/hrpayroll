Feature: URL Validation for OrangeHRM

Scenario: Launch with correct URL
    Given I launch the application with "correct"
    Then I should be redirected to the login page

Scenario: Launch with wrong URL
    Given I launch the application with "wrong"
    Then I should see a page not found error
