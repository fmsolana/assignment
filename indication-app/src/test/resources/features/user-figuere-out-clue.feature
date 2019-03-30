#Author: fmsolana
Feature: The user figure out the clue

  Scenario: Figure out a clue
    When an user figure out a clue
    Then the user go to the next clue

  Scenario: Figure out the las clue
    When an user figure out the las clue
    Then the user win the game

  Scenario: The user win the game
    When an user win the game
    Then the user see a congratualtion message
    And button for get the gift
    
    Scenario: The user claims the gift
    When an user pess button for get the gift
    Then the user see gift
    And the points obtein for win the game
    
