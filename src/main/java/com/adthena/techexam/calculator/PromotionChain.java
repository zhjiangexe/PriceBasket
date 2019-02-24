package com.adthena.techexam.calculator;

import com.adthena.techexam.dao.BuyMoreDiscountDao;
import com.adthena.techexam.dao.DiscountDao;
import com.adthena.techexam.dao.ItemDao;
import com.adthena.techexam.provider.BuyMorePromotionProvider;
import com.adthena.techexam.provider.DiscountPromotionProvider;
import com.adthena.techexam.provider.PromotionProvider;
import com.adthena.techexam.vo.CartItem;
import com.adthena.techexam.vo.Promotion;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PromotionChain {

  private final static List<PromotionProvider> PROVIDERS = new ArrayList<PromotionProvider>() {{
    add(new DiscountPromotionProvider(new DiscountDao()));
    add(new BuyMorePromotionProvider(new ItemDao(), new BuyMoreDiscountDao()));
  }};

  public List<Promotion> getPromotions(List<CartItem> cartItems) {
    return PROVIDERS.stream()
        .map(provider -> provider.provide(cartItems))
        .flatMap(List::stream)
        .collect(toList());
  }
}
