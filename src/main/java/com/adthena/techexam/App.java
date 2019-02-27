package com.adthena.techexam;

import com.adthena.techexam.calculator.Basket;
import com.adthena.techexam.calculator.BasketImp;
import com.adthena.techexam.dao.ItemDao;
import com.adthena.techexam.pojo.Discount;
import com.adthena.techexam.pojo.Item;
import com.adthena.techexam.vo.Order;
import com.adthena.techexam.vo.Promotion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.adthena.techexam.util.CurrencyUtil.poundOrPenny;

public class App {

  public static void main(String[] args) {

    System.out.printf(
        "%s%n%s%n%s%n",
        "1. Input a goods name(Soup, Bread, Milk, Apples) and press enter.",
        "2. Do not press any word, then press enter to start print order.",
        "PriceBasket:"
    );

    List<String> list = scannerSystemIn();
    Basket basket = new BasketImp();
    Order order = basket.calculateOrder(list);
    printOrder(order);
  }

  private static List<String> scannerSystemIn() {
    ItemDao itemDao = new ItemDao();
    Scanner scanner = new Scanner(System.in);
    List<String> list = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String next = scanner.nextLine().trim();
      if (next.length() == 0) {
        break;
      }
      if (itemDao.get(next) != null) {
        list.add(next);
        System.out.println(next + " * " + Collections.frequency(list, next));
      } else {
        System.out.println("This item does not exist");
      }
    }
    return list;
  }

  private static void printOrder(Order order) {
    System.out.println("========ORDER========");
    // display goods list
    order.getCartItems().stream()
        .map(cartItem ->
            String.format("%s * %d  %s",
                cartItem.getItem().getName(),
                cartItem.getQuantity(),
                poundOrPenny(
                    cartItem.getItem().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
                )
            )
        )
        .forEach(System.out::println);

    System.out.printf("%nSubtotal: %s %n", poundOrPenny(order.getSubTotal()));

    printSpecialOffer(order.getPromotionList());

    System.out.printf("Total: %s%n", poundOrPenny(order.getTotalPrice()));
  }

  private static void printSpecialOffer(List<Promotion> promotionList) {
    if (promotionList.isEmpty() || promotionList.stream().map(Promotion::getHasUsedQty).reduce((total, hasUsedQty) -> total + hasUsedQty).get() <= 0) {
      System.out.println("(No offers available)");
      return;
    }
    promotionList.stream()
        .filter(promotion -> promotion.getHasUsedQty() > 0)
        .forEach(promotion -> {
              Item item = promotion.getItem();
              BigDecimal price = item.getPrice();
              long hasUsedQty = promotion.getHasUsedQty();
              Discount discount = promotion.getDiscount();
              BigInteger discountFormat = discount.getDiscount().multiply(BigDecimal.valueOf(100)).toBigInteger();
              BigDecimal subtract = price.multiply(discount.getDiscount()).multiply(BigDecimal.valueOf(hasUsedQty));
              System.out.printf("*%s %d%% off: -%s%n", discount.getItemName(), discountFormat, poundOrPenny(subtract));
            }
        );
  }

}
