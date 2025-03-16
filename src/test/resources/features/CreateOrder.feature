Feature: Purchase the order from SauceDemo

Background: Buyer landed on SauceDemo website
    Given Buyer navigates to SauceDemo login page

Scenario: Create Order Positive Case
    Given Buyer logged to website
    When Buyer add a product to the cart
    And Buyer proceeds to checkout
    And Buyer completes the order
    Then Buyer should see the confirmation message "Thank you for your order!"
