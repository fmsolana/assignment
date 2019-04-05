#Author: fmsolana
Feature: Figure the clue

  Scenario: figure out clue
    Given user "id"
    And game "1"
    And clue 1
    And the answer "correctOption"
    When post request is come
    And the game id is correct
    And the user id is correct
    And the clue id is correct
    And the answer is correct
    Then a json with next clue is send
    And push message for countdown

  Scenario: figure out clue the last clue
    Given user "id"
    And game "1"
    And clue 3
    And the answer "0.0;0.0"
    When post request is come
    And the game id is correct
    And the user id is correct
    And the clue id is correct
    And the answer is correct
    And is the last clue
    Then a json with the gift for winner
    And a gift id is generated is send

  Scenario: Get the gift
    Given gift "id"
    And user "id"
    When get request to retrieve the gift
    And gift id is correct
    And user id is correct
    Then json with gift is send
