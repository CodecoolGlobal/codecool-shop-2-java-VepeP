package com.codecool.shop.dao;

import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Order;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    void add(Order order);

    Order get(int id);

    List<Order> getByUser(int userID) throws SQLException;

}
