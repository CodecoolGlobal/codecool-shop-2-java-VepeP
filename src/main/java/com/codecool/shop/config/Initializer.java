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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier codeCool = new Supplier("Code Cool", "Programing School and Headhunter company");
        supplierDataStore.add(codeCool);
        Supplier greenFox = new Supplier("Green Fox", "Programing School");
        supplierDataStore.add(greenFox);

        //setting up a new product category
        ProductCategory personal = new ProductCategory("Personal", "Programming", "Debugging and script writing.");
        productCategoryDataStore.add(personal);
        ProductCategory team = new ProductCategory("Team", "Project building", "Complex project building.");
        productCategoryDataStore.add(team);

        //setting up products and printing it
        productDataStore.add(new Product("Peti", new BigDecimal("250"), "$", " ", personal, codeCool));
        productDataStore.add(new Product("Saz", new BigDecimal("150"), "€", " ", personal, codeCool));
        productDataStore.add(new Product("Máté", new BigDecimal("100"), "$", " ", personal, codeCool));
        productDataStore.add(new Product("Ichy", new BigDecimal("100"), "€", " ", personal, codeCool));
        productDataStore.add(new Product("No IDEa", new BigDecimal("500"), "HUF", " ", team, greenFox));
        productDataStore.add(new Product("Martin", new BigDecimal("5"), "€", " ", personal, greenFox));
        productDataStore.add(new Product("Balázs", new BigDecimal("250"), "HUF", " ", personal, codeCool));
        productDataStore.add(new Product("Programm \uD83D\uDC55 shop", new BigDecimal("800"), "€", " ", team, codeCool));
        productDataStore.add(new Product("Eric Cartman", new BigDecimal("300"), "$", " ", personal, greenFox));
        productDataStore.add(new Product("Kenny McCormick", new BigDecimal("10"), "$", " ", personal, greenFox));
    }
}
