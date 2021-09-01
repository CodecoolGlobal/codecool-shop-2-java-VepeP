package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class CartProduct {

    private int id;
    private String name;
    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private int quantity;

    public CartProduct(int id, String name, BigDecimal defaultPrice, Currency defaultCurrency, int quantity) {
        this.id = id;
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = defaultCurrency;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defaultPrice=" + defaultPrice +
                ", quantity=" + quantity +
                ", defaultCurrency=" + defaultCurrency +
                '}';
    }
}
