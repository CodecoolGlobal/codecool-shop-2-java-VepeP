package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.HashMap;
import java.util.List;

public interface CartDao {

    void add(int id);

    void remove(int id);

    void decreaseProductQuantity(int id);

    List<Product> getAll();

}
