package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductForOrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductForOrderDaoJdbc implements ProductForOrderDao {
    private final DataSource dataSource;

    public ProductForOrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(int productID, int quantity, int orderID) {

    }

    @Override
    public List<Product> getByOrder(int orderID) {
        try (Connection conn = dataSource.getConnection()) {
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(dataSource);
            String sql = "SELECT product_id FROM products_for_order WHERE order_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderID);
            ResultSet rs = st.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                products.add(productDaoJdbc.find(rs.getInt(1)));
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading supplier with id: " + orderID, e);
        }
    }
}
