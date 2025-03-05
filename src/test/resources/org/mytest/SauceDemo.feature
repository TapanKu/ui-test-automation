@web @Sauce-Demo
Feature: SauceDemo Login

  Background:
    Given I am on the SauceDemo login page

  @smoke @test_01
  Scenario Outline: Valid login
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the inventory page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |

  @regression @test_02
  Scenario Outline: Invalid login
    When I login with username "<username>" and password "<password>"
    Then I should see an error message
    Examples:
      | username        | password     |
      | locked_out_user | secret_sauce |
      | invalid_user    | invalid_pass |

  @test_03 @regression
  Scenario: Verify session-username cookie after login
    Given I login with username "standard_user" and password "secret_sauce"
    Then the session-username cookie should have value "standard_user"
#    And the local storage should have session-username value "standard_user"


  @smoke @test_04
  Scenario: Verify items in cart after re-login
    Given I login with username "standard_user" and password "secret_sauce"
    And I add the following items to the cart:
      | itemName              |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Then I verify Add to Cart is changed to Remove for the item "Sauce Labs Backpack"
    And I verify Add to Cart is changed to Remove for the item "Sauce Labs Bike Light"
    When I logout and re-login to the application with username "standard_user" and password "secret_sauce"
    And I clicked on shopping cart icon
    Then I should see the following items in the cart:
      | itemName              |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |

  @smoke @test_05
  Scenario: Verify items should not be in cart after logout and re-login with different user
    Given I login with username "standard_user" and password "secret_sauce"
    And I add the following items to the cart:
      | itemName              |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Then I verify Add to Cart is changed to Remove for the item "Sauce Labs Backpack"
    And I verify Add to Cart is changed to Remove for the item "Sauce Labs Bike Light"
    When I logout and re-login to the application with username "performance_glitch_user" and password "secret_sauce"
    And I clicked on shopping cart icon
    Then I should not see the following items in the cart:
      | itemName              |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |

  @test_06 @regression
  Scenario: Verify checkout process
    Given I login with username "standard_user" and password "secret_sauce"
    And I add the following items to the cart:
      | itemName            |
      | Sauce Labs Backpack |
    Then I verify Add to Cart is changed to Remove for the item "Sauce Labs Backpack"
    And I clicked on shopping cart icon
    Then I should see the following items in the cart:
      | itemName            |
      | Sauce Labs Backpack |
    And I clicked on the checkout button
    And I filled the checkout form with the following details:
      | firstName | lastName | postalCode |
      | Tapan     | Sahoo    | 12345      |
    And I clicked on the continue button
    And I clicked on the finish button
    Then I should see the order confirmation message as "Thank you for your order!"