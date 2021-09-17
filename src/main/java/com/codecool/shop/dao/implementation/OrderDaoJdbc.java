package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderDaoJdbc implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(OrderDaoJdbc.class);
    private final DataSource dataSource;

    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Order order) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO order (user_id, total_price, order_status, order_date) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, order.getUser_id());
            st.setInt(2, order.getTotal_price());
            st.setString(3, order.getOrder_status());
            st.setDate(4, (Date) order.getOrder_date());
            st.executeUpdate();
        } catch (SQLException throwable) {
            logger.error("Error while adding new product to shopping cart.");
            throw new RuntimeException(throwable);
        }
    }

    @Override
    public Order get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            ProductForOrderDaoJdbc productForOrderDaoJdbc = new ProductForOrderDaoJdbc(dataSource);
            String sql = "SELECT id, user_id, total_price, order_status, order_date FROM \"order\" WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (!rs.next()) return null;
            Order order = new Order(rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDate(5),
                    productForOrderDaoJdbc.getByOrder(id));

            return order;
        } catch (SQLException e) {
            logger.error("Error while reading supplier with id: " + id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getByUser(int userID) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            ProductForOrderDaoJdbc productForOrderDaoJdbc = new ProductForOrderDaoJdbc(dataSource);
            String sql = "SELECT id, user_id, total_price, order_status, order_date FROM \"order\" WHERE user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5),
                        productForOrderDaoJdbc.getByOrder(rs.getInt(1)));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            logger.error("Error while reading supplier with id: " + userID);
            throw new RuntimeException(e);
        }
    }
}

