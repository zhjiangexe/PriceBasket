package com.jiang.techexam.provider;

import com.jiang.techexam.dao.DiscountDao;
import com.jiang.techexam.pojo.Discount;
import com.jiang.techexam.pojo.Item;
import com.jiang.techexam.vo.CartItem;
import com.jiang.techexam.vo.Promotion;

import java.util.ArrayList;
import java.util.List;

public class DiscountPromotionProvider implements PromotionProvider {
  // Injection
  private DiscountDao discountDao;

  public DiscountPromotionProvider() {
  }

  public DiscountPromotionProvider(DiscountDao discountDao) {
    this.discountDao = discountDao;
  }

  @Override
  public List<Promotion> provide(List<CartItem> cartItems) {
    List<Promotion> promotions = new ArrayList<>();
    for (CartItem cartItem : cartItems) {
      Item item = cartItem.getItem();
      String itemName = item.getName();
      Discount discount = discountDao.get(itemName);
      if (discount != null) {
        promotions.add(new Promotion<>(discount, item, cartItem.getQuantity()));
      }
    }
    return promotions;
  }
}
