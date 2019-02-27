package com.adthena.techexam.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {
  public static final NumberFormat CURRENCY_INSTANCE = NumberFormat.getCurrencyInstance(Locale.UK);

  public static String poundOrPenny(BigDecimal price) {
    if (price.compareTo(BigDecimal.ONE) >= 0) {
      return CURRENCY_INSTANCE.format(price);
    } else {
      return price.multiply(BigDecimal.valueOf(100)).toBigInteger() + "p";
    }
  }
}
