#Author: fmsolana
Feature: The countdown time show tips help

  Scenario: The countdown service
  	Given a message
  	And subscriptor
  	And time 
    When the service is call 
    Then push a message for subciptor with time in queu
    
    
  Scenario: The countdown over
  	Given a message in queue 
    When the time over 
    Then subscriptor must be notify