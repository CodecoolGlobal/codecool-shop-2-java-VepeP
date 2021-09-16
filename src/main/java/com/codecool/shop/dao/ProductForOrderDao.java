package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.List;

public interface ProductForOrderDao {

    void add(int productID, int quantity, int orderID);

    List<Product> getByOrder(int orderID);

}
