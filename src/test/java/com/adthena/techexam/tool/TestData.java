package com.adthena.techexam.tool;

import com.adthena.techexam.pojo.Item;
import com.adthena.techexam.vo.CartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {
  private final static Map<String, Item> ITEMS = new HashMap<String, Item>() {{
    put("Apples", new Item("Apples", BigDecimal.valueOf(1.00), "bag"));
    put("Milk", new Item("Milk", BigDecimal.valueOf(1.30), "bottle"));
    put("Soup", new Item("Soup", BigDecimal.valueOf(0.65), "tin"));
    put("Bread", new Item("Bread", BigDecimal.valueOf(0.80), "loaf"));
  }};

  public static List<CartItem> cart(String itemName, long count) {
    switch (itemName) {
      case "Apples":
        return cart_Apples_Milk_Soup_Bread(count, 0, 0, 0);
      case "Milk":
        return cart_Apples_Milk_Soup_Bread(0, count, 0, 0);
      case "Soup":
        return cart_Apples_Milk_Soup_Bread(0, 0, count, 0);
      case "Bread":
        return cart_Apples_Milk_Soup_Bread(0, 0, 0, count);
      default:
        return null;
    }
  }

  public static List<CartItem> cart_Apples_Milk_Soup_Bread(long apples, long milk, long soup, long bread) {
    List<CartItem> list = new ArrayList<>();
    if (apples > 0) {
      list.add(new CartItem(ITEMS.get("Apples"), apples));
    }
    if (milk > 0) {
      list.add(new CartItem(ITEMS.get("Milk"), milk));
    }
    if (soup > 0) {
      list.add(new CartItem(ITEMS.get("Soup"), soup));
    }
    if (bread > 0) {
      list.add(new CartItem(ITEMS.get("Bread"), bread));
    }
    return list;
  }
}
