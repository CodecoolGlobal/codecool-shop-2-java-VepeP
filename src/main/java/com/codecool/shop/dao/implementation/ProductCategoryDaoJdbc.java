package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDaoJdbc.class);
    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO category (department, name, description) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, category.getDepartment());
            st.setString(2, category.getName());
            st.setString(3, category.getDescription());
            st.executeUpdate();
        } catch (SQLException throwable) {
            logger.error("Error while adding new category.");
            throw new RuntimeException(throwable);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT id, name, department, description FROM category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (!rs.next()) return null;
            ProductCategory productCategory = new ProductCategory(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));

            return productCategory;
        } catch (SQLException e) {
            logger.error("Error while reading category with id: " + id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException throwable) {
            logger.error("Error while deleting category with id: " + id);
            throw new RuntimeException(throwable);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM category";
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            List<ProductCategory> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                result.add(productCategory);
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error while reading all categories");
            throw new RuntimeException(e);
        }
    }
}
