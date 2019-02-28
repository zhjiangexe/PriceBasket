package com.adthena.techexam.calculator;

import com.adthena.techexam.dao.ItemDao;
import com.adthena.techexam.vo.CartItem;
import com.adthena.techexam.vo.Order;
import com.adthena.techexam.vo.Promotion;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class OrderService {
  private ItemDao itemDao = ItemDao.getInstance();
  private Calculator calculator = new Calculator();
  private PromotionChain promotionChain = new PromotionChain();

  public Order getOrder(List<String> list) {
    List<CartItem> cartItems = this.getCartItems(list);
    return this.calculate(cartItems);
  }

  private List<CartItem> getCartItems(List<String> list) {
    Set<String> uniqueName = new HashSet<>(list);
    return uniqueName.stream()
        .map(name -> new CartItem(itemDao.get(name), Collections.frequency(list, name)))
        .collect(toList());
  }

  private Order calculate(List<CartItem> cartItems) {
    List<Promotion> promotions = promotionChain.getPromotions(cartItems);
    BigDecimal subTotal = calculator.getSubTotal(cartItems);
    BigDecimal subtract = calculator.getSubtract(cartItems, promotions);

    Order order = new Order();
    order.setCartItems(cartItems);
    order.setSubTotal(subTotal);
    order.setPromotionList(promotions);
    order.setTotalPrice(subTotal.subtract(subtract));
    return order;
  }

}
