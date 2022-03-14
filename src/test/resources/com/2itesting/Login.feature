@Run
Feature: Secure logins for the edgewords site
  In order to prevent unauthorised people accessing sensitive areas of the site
  The security team
  want admin access behind a secure login.

  Background:
    Given I am on the login page

  Scenario: Login with valid username and password
#    Given I am on the login page
    When I use valid username "edgewords" and password "edgewords123"
    Then I am logged in

@Ignore
  Scenario Outline: Check multiple login values
#    Given I am on the login page
    When I use username "<username>" and password "<password>"
    Then I am logged in
    Examples:
      | username  | password     |
      | edgewords | edgewords123 |


  Scenario: Inline table
    When I use the valid login credentials
      | username  | password     |
      | edgewords | edgewords123 |

    Then I am logged in

@Ignore
  Scenario Outline: Login with invalid usernames and passwords
#    Given I am on the login page
    When I use invalid username "<username>" and password "<password>"
    Then I am not logged in and see an error message"
    Examples:
      | username | password
      | AAA      | 123
      | BBB      | 456
      | CCC      | 789
