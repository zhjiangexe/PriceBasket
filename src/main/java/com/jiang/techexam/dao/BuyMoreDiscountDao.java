package com.jiang.techexam.dao;

import com.jiang.techexam.pojo.BuyMoreDiscount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * simulate database
 */
public class BuyMoreDiscountDao {
  private Map<String, BuyMoreDiscount> map;

  public BuyMoreDiscountDao() {
    map = new HashMap<>();
    map.put("Soup", new BuyMoreDiscount("Soup", 2, "Bread", BigDecimal.valueOf(0.5)));
  }

  public BuyMoreDiscount get(String buyMoreItemName) {
    return map.get(buyMoreItemName);
  }
}
