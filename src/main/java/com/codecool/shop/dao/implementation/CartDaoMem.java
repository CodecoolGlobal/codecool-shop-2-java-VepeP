package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.math.BigDecimal;
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
    public List<CartProduct> getAll() {
        List<CartProduct> products = new ArrayList<>();
        ProductDao productDao = ProductDaoMem.getInstance();
        Product actProduct = null;
        for (int actId : shoppingCart.keySet()) {
            actProduct = productDao.find(actId);
            products.add(new CartProduct(actId, actProduct.getName(),
                    actProduct.getDefaultPrice(), actProduct.getDefaultCurrency(), shoppingCart.get(actId)));
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
    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.valueOf(0);
        for (CartProduct product: this.getAll()) {
            BigDecimal quantity = BigDecimal.valueOf(product.getQuantity());
            BigDecimal price = product.getDefaultPrice();
            BigDecimal sum = quantity.multiply(price);

            total = total.add(sum);
        }
        return total;
    }

    @Override
    public void set(int id, int amount) {
        if (amount > 0) {
            shoppingCart.put(id, amount);
        } else {
            shoppingCart.remove(id);
        }

    }

    @Override
    public String toString() {
        return "CartDaoMem{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }

    @Override
    public void clearShoppingCart(int userID) {
        shoppingCart.clear();
    }
}
