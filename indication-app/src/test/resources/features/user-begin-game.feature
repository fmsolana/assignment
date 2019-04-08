#Author: fmsolana
Feature: User Begin the game

  Scenario: User begin a game
    When an user begin a game
    Then user see an introductory explanation and a button to continue to first clue

  Scenario: begin the game
    When an user press the button continue to first clue 
    Then the game begin and he see the first clue

   