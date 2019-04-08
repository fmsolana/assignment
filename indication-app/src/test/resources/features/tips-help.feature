#Author: fmsolana
Feature: Countdown tips

  Scenario: Clue type Choose an option
    Given a clue type Choose an option
    When the coundown over
    Then one wrong option is remove

  Scenario: Clue type write answer
    Given a clue type Choose an option
    When the coundown over
    Then some letter is show

  Scenario: Clue type be in place
    Given a clue type be in place
    When the coundown over
    Then the latitude or longitude is show
    