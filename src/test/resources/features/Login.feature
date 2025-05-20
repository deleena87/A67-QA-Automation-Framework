@smoke
Feature: Login feature
  Scenario Outline: Successfully login
Given I open login page
  When I enter email <email>
  And I enter password <password>
  And I click submit button
  Then I logged in
    Examples:
      | email                          | password |
      | "elena.skrynnikova@testpro.io" | "12345678" |

  @smoke
  Scenario: Forgot password
  Given I open login page
  When i click forgot password
  And I enter email "elena.skrynnikova@testpro.io"
  And I click submit button
  Then I expect message "We've sent a confirmation link to the email. Please continue by clicking on it"

