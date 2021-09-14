package com.codecool.shop.manager;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class CodecoolShopDbManager {
    CartDao cartDao;

    public CodecoolShopDbManager() {
    }

    public void run() {
        try {
            setup();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            System.exit(-1);
        }
    }

    private void setup() throws SQLException {
        DataSource dataSource = connect();
        cartDao = new CartDaoJdbc(dataSource);
    }



    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("codecoolshop");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin");

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }
}
