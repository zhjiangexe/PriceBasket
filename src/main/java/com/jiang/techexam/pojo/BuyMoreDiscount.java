package com.jiang.techexam.pojo;

import java.math.BigDecimal;

public class BuyMoreDiscount extends Discount {
  private String buyMoreItem;
  private long buyMoreQty;

  public BuyMoreDiscount() {
  }

  public BuyMoreDiscount(String buyMoreItem, long buyMoreQty, String itemName, BigDecimal discount) {
    this.buyMoreItem = buyMoreItem;
    this.buyMoreQty = buyMoreQty;
    this.setItemName(itemName);
    this.setDiscount(discount);
  }

  public String getBuyMoreItem() {
    return buyMoreItem;
  }

  public void setBuyMoreItem(String buyMoreItem) {
    this.buyMoreItem = buyMoreItem;
  }

  public long getBuyMoreQty() {
    return buyMoreQty;
  }

  public void setBuyMoreQty(long buyMoreQty) {
    this.buyMoreQty = buyMoreQty;
  }
}
