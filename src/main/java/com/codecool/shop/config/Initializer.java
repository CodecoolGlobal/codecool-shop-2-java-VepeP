package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.FileHandler;
import com.google.gson.Gson;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        String supplierFilePath = "src/main/resources/data/suppliers.json";
        String categoriesFilePath = "src/main/resources/data/categories.json";
        String productsFilePath = "src/main/resources/data/products.json";
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

        //FileHandler fileHandler = new FileHandler();
        //fileHandler.saveFile(fileHandler.exportSupplierDao(), fileHandler.getSupplierFile());
        //fileHandler.saveFile(fileHandler.exportProductCategoryDao(), fileHandler.getCategoryFile());
        //fileHandler.saveFile(fileHandler.exportProductDao(), fileHandler.getProductFile());

    }
}
