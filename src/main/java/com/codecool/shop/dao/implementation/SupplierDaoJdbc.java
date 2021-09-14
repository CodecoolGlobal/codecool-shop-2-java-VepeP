package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO supplier (name, description) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, supplier.getName());
            st.setString(2, supplier.getDescription());
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding new supplier.", throwable);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT id, name, description FROM supplier WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (!rs.next()) return null;
            Supplier supplier = new Supplier(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3));

            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading supplier with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM supplier WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while deleting supplier with id: " + id, throwable);
        }
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier";
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            List<Supplier> result = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                result.add(supplier);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }
}
