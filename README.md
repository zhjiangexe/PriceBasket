# PriceBasket

Requirement
- JDK8
- maven

Running

Please run `com.adthena.techexam.App` main method

1. Input a goods name(Soup, Bread, Milk, Apples) and press enter, then input next goods.
2. If you want to print order, just do not press any word, then press enter to start calculating and printing.

---
- Data from `com.adthena.techexam.dao`
    - ItemDao: save goods data
    - DiscountDao: save discount data "Apples have a 10% discount off their normal price this week"
    - BuyMoreDiscountDao: save discount data "Buy 2 tins of soup and get a loaf of bread for half price"

- Calculate and provide promotion from `com.adthena.techexam.provider`
    - DiscountPromotionProvider: find items that qualify for the discount
    - BuyMorePromotionProvider: find items that qualify for the buy more discount and calculate how many discount can get

- `com.adthena.techexam.calculator.PromotionChain`: combine `PromotionProvider`
- `com.adthena.techexam.calculator.Calculator`: calculate subtotal and reduced price
- `com.adthena.techexam.calculator.Basket`: receive input (string list) and return calculated order (includes CartItems, Subtotal, Promotions...)
- `com.adthea.techexam.App`: commandline entry and the function of printing order

