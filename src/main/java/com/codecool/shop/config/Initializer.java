package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.google.gson.Gson;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String supplierFilePath = "src/main/resources/data/suppliers.json";
        String categoriesFilePath = "src/main/resources/data/categories.json";
        String productsFilePath = "src/main/resources/data/products.json";
        String cartFilePath = "src/main/resources/data/shoppingCart.json";
        try {
            SupplierDaoMem.setInstance(new Gson().fromJson(new FileReader(supplierFilePath), SupplierDaoMem.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ProductCategoryDaoMem.setInstance(new Gson().fromJson(new FileReader(categoriesFilePath), ProductCategoryDaoMem.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ProductDaoMem.setInstance(new Gson().fromJson(new FileReader(productsFilePath), ProductDaoMem.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            CartDaoMem.setInstance(new Gson().fromJson(new FileReader(cartFilePath), CartDaoMem.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


//        FileHandler fileHandler = new FileHandler();
//        fileHandler.saveFile(fileHandler.exportCartDao(), fileHandler.getCartFile());
        //fileHandler.saveFile(fileHandler.exportSupplierDao(), fileHandler.getSupplierFile());
        //fileHandler.saveFile(fileHandler.exportProductCategoryDao(), fileHandler.getCategoryFile());
        //fileHandler.saveFile(fileHandler.exportProductDao(), fileHandler.getProductFile());

    }
}
