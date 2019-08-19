package com.jiang.techexam.pojo;

import java.math.BigDecimal;

public class Discount {
  private String itemName;
  /** 0.99 ~ 0.01 */
  private BigDecimal discount;

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }
}
