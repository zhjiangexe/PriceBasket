package com.adthena.techexam;

import com.adthena.techexam.calculator.PromotionChain;
import com.adthena.techexam.tool.TestData;
import com.adthena.techexam.vo.CartItem;
import com.adthena.techexam.vo.Promotion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionChainTest {
  private PromotionChain promotionChain = new PromotionChain();

  @ParameterizedTest(name = "{index}. Apples*{0}, Milk*{1}, Soup*{2}, Bread*{3}; expect discount item: {4}, qty={5}")
  @CsvSource({
      "1, 0, 0, 0, Apples, 1",
      "2, 0, 0, 0, Apples, 2",
      "0, 0, 2, 0, Bread, 1",
      "0, 0, 4, 0, Bread, 2"
  })
  void When_Get_Apples_Milk_Soup_Bread_Then_GetPromotion(long apples, long milk, long soup, long bread, String expectDiscountItem, long expectQty) throws Exception {
    List<CartItem> cartItems = TestData.cart_Apples_Milk_Soup_Bread(apples, milk, soup, bread);

    List<Promotion> promotions = promotionChain.getPromotions(cartItems);

    assertThat(promotions.get(0))
        .matches(p -> p.getQty() == expectQty && p.getDiscount().getItemName().equals(expectDiscountItem));
  }
}
