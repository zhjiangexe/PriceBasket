package com.jiang.techexam.calculator;

import com.jiang.techexam.dao.BuyMoreDiscountDao;
import com.jiang.techexam.dao.DiscountDao;
import com.jiang.techexam.dao.ItemDao;
import com.jiang.techexam.provider.BuyMorePromotionProvider;
import com.jiang.techexam.provider.DiscountPromotionProvider;
import com.jiang.techexam.provider.PromotionProvider;
import com.jiang.techexam.vo.CartItem;
import com.jiang.techexam.vo.Promotion;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PromotionChain {

  private final static List<PromotionProvider> PROVIDERS = new ArrayList<PromotionProvider>() {{
    add(new DiscountPromotionProvider(new DiscountDao()));
    add(new BuyMorePromotionProvider(ItemDao.getInstance(), new BuyMoreDiscountDao()));
  }};

  public List<Promotion> getPromotions(List<CartItem> cartItems) {
    return PROVIDERS.stream()
        .map(provider -> provider.provide(cartItems))
        .flatMap(List::stream)
        .collect(toList());
  }

}
