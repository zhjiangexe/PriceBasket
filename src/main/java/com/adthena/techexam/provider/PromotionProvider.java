package com.adthena.techexam.provider;

import com.adthena.techexam.vo.CartItem;
import com.adthena.techexam.vo.Promotion;

import java.util.List;

public interface PromotionProvider {
  List<Promotion> provide(List<CartItem> cartItems);
}
