package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> categories = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    public static void setInstance(ProductCategoryDaoMem instance) {
        ProductCategoryDaoMem.instance = instance;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(categories.size() + 1);
        categories.add(category);
    }

    @Override
    public ProductCategory find(int id) {
        return categories.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        categories.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return categories;
    }

    @Override
    public String toString() {
        return "ProductCategoryDaoMem{" +
                "categories=" + categories +
                '}';
    }
}
