package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
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

        //SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        //ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        //ProductDao productDataStore = ProductDaoMem.getInstance();
        //CartDaoMem cartDaoMem = CartDaoMem.getInstance();
//
        ////setting up a new supplier
        //Supplier codeCool = new Supplier("Code Cool", "Programing School and Headhunter company");
        //supplierDataStore.add(codeCool);
        //Supplier greenFox = new Supplier("Green Fox", "Programing School");
        //supplierDataStore.add(greenFox);
//
        //ProductCategory personal = new ProductCategory("Personal", "Programming", "Debugging and script writing.");
        //productCategoryDataStore.add(personal);
        //ProductCategory team = new ProductCategory("Team", "Project building", "Complex project building.");
        //productCategoryDataStore.add(team);
//
        ////setting up products and printing it
        //productDataStore.add(new Product("Peti", new BigDecimal("250"), "USD", "_", personal, codeCool));
        //productDataStore.add(new Product("Saz", new BigDecimal("150"), "EUR", "_", personal, codeCool));
        //productDataStore.add(new Product("Mate", new BigDecimal("100"), "USD", "_", personal, codeCool));
        //productDataStore.add(new Product("Ichy", new BigDecimal("100"), "USD", " ", personal, codeCool));
        //productDataStore.add(new Product("Balazs", new BigDecimal("250"), "HUF", " ", personal, codeCool));
        //productDataStore.add(new Product("Martin", new BigDecimal("5"), "EUR", " ", personal, greenFox));
        //productDataStore.add(new Product("Eric Cartman", new BigDecimal("300"), "USD", " ", personal, greenFox));
        //productDataStore.add(new Product("Kenny McCormick", new BigDecimal("10"), "USD", " ", personal, greenFox));
        //productDataStore.add(new Product("ProgramING shop", new BigDecimal("800"), "EUR", " ", team, codeCool));
        //productDataStore.add(new Product("No IDEa", new BigDecimal("500"), "HUF", " ", team, greenFox));
//
        //FileHandler fileHandler = new FileHandler();
        //fileHandler.saveFile(fileHandler.exportSupplierDao(), fileHandler.getSupplierFile());
        //fileHandler.saveFile(fileHandler.exportProductCategoryDao(), fileHandler.getCategoryFile());
        //fileHandler.saveFile(fileHandler.exportProductDao(), fileHandler.getProductFile());
        ////fileHandler.saveFile(fileHandler.exportCartDao(), fileHandler.getCartFile());

    }
}
