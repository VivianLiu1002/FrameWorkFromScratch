@regression
Feature: Testing Item Purchase Functionality of SauceDemo

  Scenario Outline: Validating Order Functionality is working for an item (Happy Path)
    Given User provides username and password to login successfully
    When User chooses the "<productName>", click add to cart button and validates it is added
    And User clicks cart icon and checkout button
    And User provides '<firstname>','<lastname>','<zipCode>' to checkout page and clicks continue button
    Then User validates the '<productName>','<itemTotal>','<tax>','<totalPrice>' with '8'% tax rate
    And User clicks finish button and validate 'message' for purchase
    Examples:
      | productName         | firstname | lastname | zipCode | itemTotal | tax | totalPrice |
      | Sauce Labs Backpack | AHmet     | Baldir   | 60134   | 29.99     | 2.4 | 3          |

