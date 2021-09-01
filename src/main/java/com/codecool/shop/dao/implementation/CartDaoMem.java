package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem implements CartDao {
    private HashMap<Integer, Integer> shoppingCart = null;
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
            instance.shoppingCart = new HashMap<>();
        }
        return instance;
    }

    public static void setInstance(CartDaoMem instance) {
        CartDaoMem.instance = instance;
    }

    @Override
    public void add(int id) {
        if (checkIfProductInCart(id))
            increaseProductQuantity(id);
        else
            shoppingCart.put(id, 1);
    }

    @Override
    public void remove(int id) {
        for (int actId : shoppingCart.keySet()) {
            if (id == actId) shoppingCart.remove(actId);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        ProductDao productDao = ProductDaoMem.getInstance();
        for (int actId : shoppingCart.keySet()) {
            products.add(productDao.find(actId));
        }
        return products;
    }

    private void increaseProductQuantity(Integer product) {
        Integer quantity = shoppingCart.get(product);
        if (quantity != null) shoppingCart.put(product, ++quantity);
    }

    @Override
    public void decreaseProductQuantity(int id) {
        Integer quantity = shoppingCart.get(id);
        if (quantity != null && quantity > 1) shoppingCart.put(id, --quantity);
        else if (quantity != null) shoppingCart.remove(id);
    }

    private boolean checkIfProductInCart(int id) {
        if (shoppingCart == null) return false;
        for (int actId : shoppingCart.keySet())
            if (id == actId) return true;
        return false;
    }

    @Override
    public String toString() {
        return "CartDaoMem{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}

/*
public class CartDaoMem implements CartDao {
    private HashMap<Product, Integer> shoppingCart = null;
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
            instance.shoppingCart = new HashMap<>();
        }
        return instance;
    }

    public static void setInstance(CartDaoMem instance) {
        CartDaoMem.instance = instance;
    }

    @Override
    public void add(Product product) {
        if (checkIfProductInCart(product)) increaseProductQuantity(product);
        else shoppingCart.put(product, 1);
    }

    @Override
    public Product find(int id) {
        for (Product myProduct : shoppingCart.keySet()) {
            if (id == myProduct.getId()) return myProduct;
        }
        return null;
    }

    @Override
    public void remove(int id) {
        for (Product myProduct : shoppingCart.keySet()) {
            if (id == myProduct.getId()) shoppingCart.remove(myProduct);
        }
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return shoppingCart;
    }

    private void increaseProductQuantity(Product product) {
        Integer quantity = shoppingCart.get(product);
        if (quantity != null) shoppingCart.put(product, ++quantity);
    }

    @Override
    public void decreaseProductQuantity(Product product) {
        Integer quantity = shoppingCart.get(product);
        if (quantity != null && quantity > 2) shoppingCart.put(product, --quantity);
        else if (quantity != null) remove(product.getId());
    }

    private boolean checkIfProductInCart(Product product) {
        if (shoppingCart == null) return false;
        for (Product myProduct : shoppingCart.keySet())
            if (myProduct.equals(product)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "CartDaoMem{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
*/