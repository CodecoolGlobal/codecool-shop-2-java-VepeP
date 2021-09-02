package com.codecool.shop.dao;

import com.codecool.shop.model.CartProduct;

import java.math.BigDecimal;
import java.util.List;

public interface CartDao {

    void add(int id);

    void remove(int id);

    void decreaseProductQuantity(int id);

    List<CartProduct> getAll();

    BigDecimal getTotalPrice();

}
