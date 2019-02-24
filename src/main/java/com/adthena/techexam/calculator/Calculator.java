package com.adthena.techexam.calculator;

import com.adthena.techexam.pojo.Discount;
import com.adthena.techexam.pojo.Item;
import com.adthena.techexam.vo.CartItem;
import com.adthena.techexam.vo.Promotion;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Calculator {

  public BigDecimal getSubTotal(List<CartItem> cartItems) {
    BigDecimal subTotal = BigDecimal.ZERO;
    for (CartItem cartItem : cartItems) {
      subTotal = subTotal.add(cartItem.getItem().getPrice()
          .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
    }
    return subTotal;
  }

  public BigDecimal getSubtract(List<CartItem> cartItems, List<Promotion> promotions) {
    BigDecimal subtract = BigDecimal.ZERO;
    for (CartItem cartItem : cartItems) {
      Item item = cartItem.getItem();
      String itemName = item.getName();
      List<Promotion> promotionCollect = promotions.stream()
          .filter(promotion ->
              promotion.getDiscount().getItemName().equals(itemName)
                  && (promotion.getQty() > promotion.getHasUsedQty())
          )
          .collect(toList());

      long cartItemQuantity = cartItem.getQuantity();
      for (Promotion promotion : promotionCollect) {
        long available = promotion.getQty() - promotion.getHasUsedQty();
        long remaining = cartItemQuantity - available;
        long use = remaining >= 0 ? available : cartItemQuantity;
        promotion.setHasUsedQty(promotion.getHasUsedQty() + use);
        Discount discount = promotion.getDiscount();
        BigDecimal off = item.getPrice().multiply(discount.getDiscount()).multiply(BigDecimal.valueOf(use));
        subtract = subtract.add(off);
      }

    }
    return subtract;
  }


}
