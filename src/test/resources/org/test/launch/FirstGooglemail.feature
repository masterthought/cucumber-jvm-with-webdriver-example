Feature: In order to optimise automation   As a developer
  I want the browser to remain open during testing this is first instance


  Scenario: Keeping the browser open
    Given I have no Browser instances
    And I have opened my browser No 1
    When I login with username:'testa481@gmail.com' and password 'password12345678'
    Then login was successful

  Scenario Outline: Allow Multiple logins in the same browser with correct detection of login success
    Given I have opened my browser No 2
    And I login with username:'<username>' and password '<password>'
    Then login was <loginSuccess>

  Examples:
    | username           | password         | loginSuccess |
    | tester4            | tester           | unsuccessful |
    | tester5            | tester           | unsuccessful |
    | tester6            | tester           | unsuccessful |
    | testa481@gmail.com | password12345678 | successful   |
    | tester7            | tester           | unsuccessful |
    | tester8            | tester           | unsuccessful |

  Scenario: support more browser sessions
    Given I have opened my browser No 3
    And I login with username:'tester3' and password 'tester'
    And I visit http://www.bbc.co.uk
    And I am using browser No 1
    And I visit http://www.bbc.co.uk
    Then I have 3 Browser instances
