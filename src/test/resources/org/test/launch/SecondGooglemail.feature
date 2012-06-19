Feature: In order to optimise automation
  As a developer
  I want the browser to remain open during testing this is second instance and should use existing instances that have been created during the test suite


  Scenario: Keeping the browser open
    Given I have 3 Browser instances
    And I have opened my browser No 1
    And I login with username:'tester1' and password 'tester'
    And I have opened my browser No 2
    And I login with username:'tester2' and password 'tester'
    And I have opened my browser No 3
    And I login with username:'tester3' and password 'tester'
    And I visit http://www.bbc.co.uk
    And I am using browser No 1
    And I visit http://www.bbc.co.uk
    Then I have 3 Browser instances
