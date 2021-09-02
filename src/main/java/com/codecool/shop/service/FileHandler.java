package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Objects;

public class FileHandler {
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

    public File getSupplierFile() {
        return new File("src/main/resources/data/suppliers.json");
    }

    public File getCategoryFile() {
        return new File("src/main/resources/data/categories.json");
    }

    public File getProductFile() {
        return new File("src/main/resources/data/products.json");
    }

    public File getCartFile() {
        return new File("src/main/resources/data/shoppingCart.json");
    }

    public File getOrderFile(int orderNum) {
        return new File("src/main/resources/data/" + orderNum + "_order.json");
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

    public String exportCartDao() {
        CartDao cartDataStore = CartDaoMem.getInstance();
        return gson.toJson(cartDataStore);
    }

    public int getNextOrderID() {
        File folder = new File("src/main/resources/data");
        File[] listOfFiles = folder.listFiles();
        int nextOrderId = 1;
        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains("order")) nextOrderId += 1;
        }
        return nextOrderId;
    }

    public Order getOrderFromFile(String orderId) throws FileNotFoundException {
        String orderFilePath = "src/main/resources/data/" + orderId + "_order.json";
        return (new Gson().fromJson(new FileReader(orderFilePath), Order.class));
    }
}
