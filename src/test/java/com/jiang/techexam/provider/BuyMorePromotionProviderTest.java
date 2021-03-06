package com.jiang.techexam.provider;

import com.jiang.techexam.dao.BuyMoreDiscountDao;
import com.jiang.techexam.dao.ItemDao;
import com.jiang.techexam.pojo.BuyMoreDiscount;
import com.jiang.techexam.pojo.Item;
import com.jiang.techexam.tool.TestData;
import com.jiang.techexam.vo.CartItem;
import com.jiang.techexam.vo.Promotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BuyMorePromotionProviderTest {
  private PromotionProvider promotionProvider;
  private ItemDao mockItemDao;
  private BuyMoreDiscountDao buyMoreDiscountDao;

  @BeforeEach
  void beforeAll() {
    mockItemDao = mock(ItemDao.class);
    buyMoreDiscountDao = mock(BuyMoreDiscountDao.class);
    promotionProvider = new BuyMorePromotionProvider(mockItemDao, buyMoreDiscountDao);
  }

  @DisplayName("When brought item quantity small than discount specific buyMoreQty, then have no promotion")
  @ParameterizedTest(name = "{index}. Soup*{0}, buyMoreQty:{1}, have no promotion")
  @CsvSource({
      "1, 2",
      "2, 3"
  })
  void provide1(long soupQty, long buyMoreQty) {
    List<CartItem> cartItems = TestData.cart_Apples_Milk_Soup_Bread(0, 0, soupQty, 0);
    when(buyMoreDiscountDao.get("Soup")).thenReturn(new BuyMoreDiscount("Soup", buyMoreQty, "Bread", BigDecimal.valueOf(0.5)));
    when(mockItemDao.get("Bread")).thenReturn(new Item("Bread", BigDecimal.valueOf(0.80), "loaf"));

    List<Promotion> promotions = promotionProvider.provide(cartItems);

    assertThat(promotions).isEmpty();
  }

  @DisplayName("When give brought item quantity and discount specific buyMoreQty, then get promotion soupQty / buyMoreQty")
  @ParameterizedTest(name = "{index}. Soup*{0}, buyMoreQty:{1}; expect promotion qty={2}")
  @CsvSource({
      "2, 1, 2",
      "2, 2, 1",
      "3, 2, 1"
  })
  void provide2(long soupQty, long buyMoreQty, long expectQty) {
    List<CartItem> cartItems = TestData.cart_Apples_Milk_Soup_Bread(0, 0, soupQty, 0);
    when(buyMoreDiscountDao.get("Soup")).thenReturn(new BuyMoreDiscount("Soup", buyMoreQty, "Bread", BigDecimal.valueOf(0.5)));
    when(mockItemDao.get("Bread")).thenReturn(new Item("Bread", BigDecimal.valueOf(0.80), "loaf"));

    List<Promotion> promotions = promotionProvider.provide(cartItems);

    assertThat(promotions.get(0).getQty()).isEqualTo(expectQty);
  }

}
