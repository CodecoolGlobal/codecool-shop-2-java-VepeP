package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> products = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    public static void setInstance(ProductDaoMem instance) {
        ProductDaoMem.instance = instance;
    }

    @Override
    public void add(Product product) {
        product.setId(products.size() + 1);
        products.add(product);
    }

    @Override
    public Product find(int id) {
        return products.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        products.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> productList = new ArrayList<>();
        for (Product product: products)
            if (product.getSupplier().getId() == supplier.getId()) productList.add(product);
        return productList;
        //return products.stream().filter(t -> t.getSupplier().getId() == supplier.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return products.stream().filter(t -> t.getProductCategory().getId() == (productCategory.getId())).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ProductDaoMem{" +
                "products=" + products +
                '}';
    }
}
