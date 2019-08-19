package com.jiang.techexam.vo;

import com.jiang.techexam.pojo.Discount;
import com.jiang.techexam.pojo.Item;

public class Promotion<T extends Discount> {
  private T discount;
  private Item item;
  private long qty;
  private long hasUsedQty;

  public Promotion(T discount, Item item, long qty) {
    this.discount = discount;
    this.item = item;
    this.qty = qty;
  }

  public T getDiscount() {
    return discount;
  }

  public void setDiscount(T discount) {
    this.discount = discount;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public long getQty() {
    return qty;
  }

  public void setQty(long qty) {
    this.qty = qty;
  }

  public long getHasUsedQty() {
    return hasUsedQty;
  }

  public void setHasUsedQty(long hasUsedQty) {
    this.hasUsedQty = hasUsedQty;
  }
}
