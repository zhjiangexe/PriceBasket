package com.jiang.techexam.vo;

import java.math.BigDecimal;
import java.util.List;

public class Order {
  private List<CartItem> cartItems;
  private BigDecimal subTotal;
  private BigDecimal totalPrice;
  private List<Promotion> promotionList;

  public List<CartItem> getCartItems() {
    return cartItems;
  }

  public void setCartItems(List<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  public BigDecimal getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(BigDecimal subTotal) {
    this.subTotal = subTotal;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public List<Promotion> getPromotionList() {
    return promotionList;
  }

  public void setPromotionList(List<Promotion> promotionList) {
    this.promotionList = promotionList;
  }
}
