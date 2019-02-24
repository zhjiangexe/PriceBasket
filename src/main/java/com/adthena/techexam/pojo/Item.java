package com.adthena.techexam.pojo;

import java.math.BigDecimal;

public class Item {
  private String name;
  private BigDecimal price;
  private String unit;

  public Item(String name, BigDecimal price, String unit) {
    this.name = name;
    this.price = price;
    this.unit = unit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

}
