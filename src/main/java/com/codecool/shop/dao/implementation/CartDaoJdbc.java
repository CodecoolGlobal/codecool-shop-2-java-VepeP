package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartProduct;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CartDaoJdbc implements CartDao {
    private final DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO shopping_cart (product_id, quantity) VALUES (?, 1)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding new product to shopping cart.", throwable);
        }
    }

    @Override
    public void set(int id, int amount) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE shopping_cart SET quantity = ? WHERE product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, amount);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while updating shopping cart.", throwable);
        }
    }

    @Override
    public List<CartProduct> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT sc.product_id, p.name, p.default_price, p.default_currency, sc.quantity FROM shopping_cart sc INNER JOIN product p on p.id = sc.product_id";
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            List<CartProduct> result = new ArrayList<>();
            while (rs.next()) {
                CartProduct cartProduct = new CartProduct(rs.getInt(1),
                        rs.getString(2),
                        new BigDecimal(rs.getInt(3)),
                        Currency.getInstance(rs.getString(4)),
                        rs.getInt(5));
                result.add(cartProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products from shopping cart", e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM shopping_cart WHERE product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while deleting product from shopping cart.", throwable);
        }
    }

    @Override
    public void decreaseProductQuantity(int id) {
    }


    @Override
    public BigDecimal getTotalPrice() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT p.default_price*sc.quantity FROM shopping_cart sc INNER JOIN product p on p.id = sc.product_id";
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            int totalPrice = 0;
            while (rs.next()) {
                totalPrice += rs.getInt(1);
            }
            return new BigDecimal(totalPrice);
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting total price of shopping cart", e);
        }
    }

    @Override
    public void clearShoppingCart(int userID) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while deleting all product from shopping cart.", throwable);
        }
    }

    @Override
    public String toString() {
        return "CartDaoJdbc{" +
                "dataSource=" + dataSource +
                '}';
    }
}
