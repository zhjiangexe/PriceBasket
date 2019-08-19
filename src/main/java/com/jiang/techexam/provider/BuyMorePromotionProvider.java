package com.jiang.techexam.provider;

import com.jiang.techexam.dao.BuyMoreDiscountDao;
import com.jiang.techexam.dao.ItemDao;
import com.jiang.techexam.pojo.BuyMoreDiscount;
import com.jiang.techexam.pojo.Item;
import com.jiang.techexam.vo.CartItem;
import com.jiang.techexam.vo.Promotion;

import java.util.ArrayList;
import java.util.List;

public class BuyMorePromotionProvider implements PromotionProvider {
  // Injection
  private BuyMoreDiscountDao buyMoreDiscountDao;
  private ItemDao itemDao;

  public BuyMorePromotionProvider() {
  }

  public BuyMorePromotionProvider(ItemDao itemDao, BuyMoreDiscountDao buyMoreDiscountDao) {
    this.itemDao = itemDao;
    this.buyMoreDiscountDao = buyMoreDiscountDao;
  }

  @Override
  public List<Promotion> provide(List<CartItem> cartItems) {
    List<Promotion> promotions = new ArrayList<>();
    for (CartItem cartItem : cartItems) {
      BuyMoreDiscount buyMoreDiscount = buyMoreDiscountDao.get(cartItem.getItem().getName());
      if (buyMoreDiscount != null) {
        long promotionCount = cartItem.getQuantity() / buyMoreDiscount.getBuyMoreQty();
        if (promotionCount > 0) {
          Item item = itemDao.get(buyMoreDiscount.getItemName());
          promotions.add(new Promotion<>(buyMoreDiscount, item, promotionCount));
        }
      }
    }
    return promotions;
  }

}
