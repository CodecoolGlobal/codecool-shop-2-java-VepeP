package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileHandler {
    //private final Gson gson = new Gson();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public FileHandler() {
    }

    public void saveFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public File getSupplierFile(){
        return new File("src/main/resources/data/suppliers.json");
    }

    public File getCategoryFile(){
        return new File("src/main/resources/data/categories.json");
    }

    public File getProductFile(){
        return new File("src/main/resources/data/products.json");
    }

    public String exportProductDao() {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        return gson.toJson(productDataStore);
    }

    public String exportProductCategoryDao() {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        return gson.toJson(productCategoryDataStore);
    }

    public String exportSupplierDao() {
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        return gson.toJson(supplierDataStore);
    }
}
