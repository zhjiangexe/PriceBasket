package com.adthena.techexam.dao;

import com.adthena.techexam.pojo.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * simulate database
 */
public class ItemDao {
  private Map<String, Item> items;
  private static ItemDao instance = null;

  public static ItemDao getInstance() {
    if (instance == null) {
      instance = new ItemDao();
      return instance;
    }
    return instance;
  }

  public ItemDao() {
    items = new HashMap<>();
    items.put("Soup", new Item("Soup", BigDecimal.valueOf(0.65), "tin"));
    items.put("Bread", new Item("Bread", BigDecimal.valueOf(0.80), "loaf"));
    items.put("Milk", new Item("Milk", BigDecimal.valueOf(1.30), "bottle"));
    items.put("Apples", new Item("Apples", BigDecimal.valueOf(1.00), "bag"));
  }

  public Item get(String name) {
    return items.get(name);
  }
}
