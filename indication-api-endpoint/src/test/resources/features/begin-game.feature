#Author: fmsolana
Feature: Begin the game

  Scenario: Get information about game
    When get request with game 1
    Then a json respone with introductory explanation is send

  Scenario: begin the game
    Given an user "id" and game 1
    When a begin game post request come
    Then the game start
    And a json respone is send with first clue
    And service for coutdown is invoke
