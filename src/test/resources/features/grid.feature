@allByGrid
Feature: Checking some functionality auto.am website using Selenium grid

  Background: User navigates to auto.am using Selenium grid
    Given Go to auto.am page using Selenium grid

  @firstTestCaseByGrid
  Scenario: Check connection of filter combo boxes using Selenium grid
    When Entering values in the combo boxes using Selenium grid

  @secondTestCaseByGrid
  Scenario: Check that all prices in result are smaller than selected upper bound of price using Selenium grid
    When Selecting index of upper bound taking all prices using Selenium grid
    Then All prices in result are smaller than selected upper bound using Selenium grid

  @thirdTestCaseByGrid
  Scenario: Filter By Audi. Check that actual items count matches with the number in green box using Selenium grid
    When Filter by Audi taking actual items count using Selenium grid
    Then Actual items count matches with the number in green box using Selenium grid


