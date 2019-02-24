package com.adthena.techexam.calculator;

import com.adthena.techexam.vo.Order;

import java.util.List;

public interface Basket {
  Order calculateOrder(List<String> list);
}
