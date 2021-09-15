package com.codecool.shop.model;

import java.util.Date;
import java.util.List;

public class Order {

    private int user_id;
    private int total_price;
    private String order_status;
    private Date order_date;
    private List<Product> products;

    public Order(int user_id, int total_price, String order_status, Date order_date, List<Product> products) {
        this.user_id = user_id;
        this.total_price = total_price;
        this.order_status = order_status;
        this.order_date = order_date;
        this.products = products;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user_id=" + user_id +
                ", total_price=" + total_price +
                ", order_status='" + order_status + '\'' +
                ", order_date=" + order_date +
                ", products=" + products +
                '}';
    }
}