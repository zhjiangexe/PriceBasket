package com.adthena.techexam;

import com.adthena.techexam.calculator.Calculator;
import com.adthena.techexam.calculator.PromotionChain;
import com.adthena.techexam.tool.TestData;
import com.adthena.techexam.vo.CartItem;
import com.adthena.techexam.vo.Promotion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

  private Calculator calculator = new Calculator();
  private PromotionChain promotionChain = new PromotionChain();

  @ParameterizedTest(name = "{index}. Apples*{0}, Milk*{1}, Soup*{2}, Bread*{3}, expect subTotal={4}")
  @CsvSource({
      "0, 1, 1, 1, 2.75",
      "1, 1, 1, 1, 3.75",
      "1, 1, 2, 1, 4.4",
      "2, 1, 4, 1, 6.7",
      "2, 1, 4, 2, 7.5"
  })
  void When_Get_Apples_Milk_Soup_Bread_Then_SubTotalEqual(long apples, long milk, long soup, long bread, double expectSubtract) throws Exception {
    List<CartItem> cartItems = TestData.cart_Apples_Milk_Soup_Bread(apples, milk, soup, bread);

    BigDecimal subTotal = calculator.getSubTotal(cartItems);

    assertThat(subTotal).isEqualByComparingTo(BigDecimal.valueOf(expectSubtract));
  }

  @ParameterizedTest(name = "{index}. Apples*{0}, Milk*{1}, Soup*{2}, Bread*{3}, expect subtract={4}")
  @CsvSource({
      "0, 1, 1, 1, 0",
      "1, 1, 1, 1, 0.1",
      "1, 1, 2, 1, 0.5",
      "2, 1, 4, 1, 0.6",
      "2, 1, 4, 2, 1.0"
  })
  void When_Get_Apples_Milk_Soup_Bread_Then_SubtractEqual(long apples, long milk, long soup, long bread, double expectSubtract) throws Exception {
    List<CartItem> cartItems = TestData.cart_Apples_Milk_Soup_Bread(apples, milk, soup, bread);
    List<Promotion> promotions = promotionChain.getPromotions(cartItems);

    BigDecimal subtract = calculator.getSubtract(cartItems, promotions);

    assertThat(subtract).isEqualByComparingTo(BigDecimal.valueOf(expectSubtract));
  }

}
