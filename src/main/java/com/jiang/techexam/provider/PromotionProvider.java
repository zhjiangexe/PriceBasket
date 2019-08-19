package com.jiang.techexam.provider;

import com.jiang.techexam.vo.CartItem;
import com.jiang.techexam.vo.Promotion;

import java.util.List;

public interface PromotionProvider {
  List<Promotion> provide(List<CartItem> cartItems);
}
