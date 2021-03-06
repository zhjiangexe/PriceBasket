# PriceBasket

## Environment

- Windows10 / Ubuntu
- JDK8
- Developed by Intellij IDEA 2018.3 on Windows10
- Gradle Wrapper: Gradle 5.2.1

## Start

1. Download project `git clone git@github.com:zhjiangexe/PriceBasket.git`
2. Go to folder `cd PriceBasket`
3. Execute `./gradlew jar` or `gradlew jar` generate jar file `pricebasket.jar` under `build/libs`
4. Go to folder `cd build/libs`, execute `java -jar pricebasket.jar`
5. Running
    1. Input a goods name(Soup, Bread, Milk, Apples) and press enter, then input next goods.
    2. If you want to print order, just do not press any word, then press enter to start calculating and printing.
6. Testing
    1. `./gradlew test` or `gradlew test`execute testing and get testing report
    2. open `build/reports/tests/test/index.html` to check testing reports
7. IDE
    - Eclipse: File > Import > Gradle > Existing Gradle Project > Select this PriceBasket folder as Project root directory >
    - Intellij: Just open project(Select this PriceBasket folder) 
    
---
## Packages

- Data from `com.jiang.techexam.dao`
    - ItemDao: save goods data
    - DiscountDao: save discount data "Apples have a 10% discount off their normal price this week"
    - BuyMoreDiscountDao: save discount data "Buy 2 tins of soup and get a loaf of bread for half price"

- Calculate and provide promotion from `com.jiang.techexam.provider`
    - DiscountPromotionProvider: find items that qualify for the discount
    - BuyMorePromotionProvider: find items that qualify for the buy more discount and calculate how many discount can get

- `com.jiang.techexam.calculator.PromotionChain`: combine `PromotionProvider`
- `com.jiang.techexam.calculator.Calculator`: calculate subtotal and reduced price
- `com.jiang.techexam.calculator.OrderService`: receive input (string list) and return calculated order (includes CartItems, Subtotal, Promotions...)
- `com.jiang.techexam.App`: commandline entry and the function of printing order

---
## Building Steps & Design

Requirements have goods, give the **Items** with attributes name, price and unit

- Soup – 65p per tin
- Bread – 80p per loaf
- Milk – £1.30 per bottle
- Apples – £1.00 per bag
    
Requirements have discount, give the **Discount** with attributes name and discount, then second sentence states its condition, so give the **BuyMoreDiscount** with attributes buyMoreItem and buyMoreQty

- Apples have a 10% discount off their normal price this week
- Buy 2 tins of soup and get a loaf of bread for half price

Through `App` provide customer to input goods list

Through `OrderService` get items information and save the quantity of purchase in **CartItem**

Through `PromotionChain`(DiscountPromotionProvider, BuyMorePromotionProvider) to calculate obtainable **Promotion** with attribute category, qty and hasUsedQty

Through `Calculator` to get subtotal and totalsubtract

Then `OrderService` return **Order** of combining the promotions, subtotal and total discount price

finally `App` print out information for customer

---
`Promotion` use Generic to accept different **Discount**

interface `PromotionProvider` defined method `List<Promotion> provide(List<CartItem> cartItems)` can be different discount condition implements

`PromotionChain` combine two `PromotionProvider` implements