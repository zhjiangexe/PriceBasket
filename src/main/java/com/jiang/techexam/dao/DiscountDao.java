package com.jiang.techexam.dao;

import com.jiang.techexam.pojo.Discount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * simulate database
 */
public class DiscountDao {

  private Map<String, Discount> map;

  public DiscountDao() {
    map = new HashMap<>();
    Discount discount = new Discount();
    discount.setItemName("Apples");
    discount.setDiscount(BigDecimal.valueOf(0.1));
    map.put("Apples", discount);
  }

  public Discount get(String itemName) {
    return map.get(itemName);
  }
}
