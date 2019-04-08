#Author: fmsolana
Feature: The type of clue

  Scenario: Clue type Choose an option
    When The clue is type  Choose an option
    Then 3 options is show to user

  Scenario: Clue type write answer
    When The clue is type  write answer
    Then input for write is show

  Scenario: Clue type be in place
    When The clue is type  be in place
    Then input for write is show for write geographical coordinates
