package com.adthena.techexam.provider;

import com.adthena.techexam.dao.DiscountDao;
import com.adthena.techexam.pojo.Discount;
import com.adthena.techexam.tool.TestData;
import com.adthena.techexam.vo.CartItem;
import com.adthena.techexam.vo.Promotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiscountPromotionProviderTest {

  private PromotionProvider promotionProvider;
  private DiscountDao discountDao;

  @BeforeEach
  void beforeAll() {
    discountDao = mock(DiscountDao.class);
    promotionProvider = new DiscountPromotionProvider(discountDao);
  }

  @DisplayName("When buy A item specific quantity Then return A item specific quantity promotion")
  @ParameterizedTest(name = "{index}. {0}*{1}, gain promotion {2}*{3}")
  @CsvSource({
      "Apples, 1, Apples, 1",
      "Apples, 2, Apples, 2"
  })
  void provide(String itemName, long itemCount, String exceptItemName, long exceptItemCount) {
    List<CartItem> cartItems = TestData.cart(itemName, itemCount);
    Discount discount = new Discount();
    discount.setItemName(itemName);
    when(discountDao.get(any())).thenReturn(discount);

    List<Promotion> promotions = promotionProvider.provide(cartItems);

    assertThat(promotions.get(0))
        .matches(promotion -> promotion.getQty() == exceptItemCount && promotion.getDiscount().getItemName().equals(exceptItemName));
  }
}