#Author: fmsolana
Feature: Begin the game

  Scenario: Get information about game
    When get request to  "url" with game "id"
    Then a json respone with introductory explanation is send

  Scenario: begin the game
    Given an user id and game id
    When a post request come
    Then the game start
    And a json respone is send with first clue
    And service for coutdown is invoke
