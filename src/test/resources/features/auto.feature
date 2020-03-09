@all
Feature: Checking some functionality auto.am website

  Background: User navigates to auto.am
    Given Go to auto.am page

  @firstTestCase
  Scenario: Check connection of filter combo boxes
    When Entering values in the combo boxes

  @secondTestCase
  Scenario: Check that all prices in result are smaller than selected upper bound of price
    When Selecting index of upper bound taking all prices
    Then All prices in result are smaller than selected upper bound

  @thirdTestCase
  Scenario: Filter By Audi. Check that actual items count matches with the number in green box
    When Filter by Audi taking actual items count
    Then Actual items count matches with the number in green box


