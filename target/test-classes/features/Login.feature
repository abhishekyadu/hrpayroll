Feature: Login functionality for OrangeHRM

Scenario: Positive Login
    Given I launch the application with "correct"
    Given I am logged out
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the dashboard

Scenario: Negative Login - Invalid credentials
    Given I am logged out
    When I login with username "wrongUser" and password "wrongPass"
    Then I should see a login error message "Invalid credentials"