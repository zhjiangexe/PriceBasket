package com.adthena.techexam.vo;

import com.adthena.techexam.pojo.Item;

public class CartItem {
  private Item item;
  private long quantity;

  public CartItem(Item item, long quantity) {
    this.item = item;
    this.quantity = quantity;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
