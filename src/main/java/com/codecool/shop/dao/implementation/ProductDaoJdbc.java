package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoJdbc.class);
    private final DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (id, default_price, default_currency, category_id, supplier_id, name, description)" +
                         " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, product.getId());
            st.setInt(2, product.getDefaultPrice().intValue());
            st.setString(3, product.getDefaultCurrency().getCurrencyCode());
            st.setInt(4, product.getProductCategory().getId());
            st.setInt(5, product.getSupplier().getId());
            st.setString(6, product.getName());
            st.setString(7, product.getDescription());
            st.executeUpdate();
        } catch (SQLException throwable) {
            logger.error("Error while adding new product.");
            throw new RuntimeException(throwable);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id FROM product WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (!rs.next()) return null;
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
            Product product = new Product(rs.getInt(1),
                    rs.getString(2),
                    new BigDecimal(rs.getInt(3)),
                    rs.getString(4),
                    rs.getString(5),
                    productCategoryDaoJdbc.find(rs.getInt(6)),
                    supplierDaoJdbc.find(rs.getInt(7)));

            return product;
        } catch (SQLException e) {
            logger.error("Error while reading product with id: " + id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException throwable) {
            logger.error("Error while deleting product with id: " + id);
            throw new RuntimeException(throwable);
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id FROM product";
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            List<Product> result = new ArrayList<>();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        new BigDecimal(rs.getInt(3)),
                        rs.getString(4),
                        rs.getString(5),
                        productCategoryDaoJdbc.find(rs.getInt(6)),
                        supplierDaoJdbc.find(rs.getInt(7)));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error while reading all categories");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id FROM product where supplier_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        new BigDecimal(rs.getInt(3)),
                        rs.getString(4),
                        rs.getString(5),
                        productCategoryDaoJdbc.find(rs.getInt(6)),
                        supplierDaoJdbc.find(rs.getInt(7)));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error while reading all categories");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id FROM product where category_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        new BigDecimal(rs.getInt(3)),
                        rs.getString(4),
                        rs.getString(5),
                        productCategoryDaoJdbc.find(rs.getInt(6)),
                        supplierDaoJdbc.find(rs.getInt(7)));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error while reading all categories");
            throw new RuntimeException(e);
        }
    }
}
