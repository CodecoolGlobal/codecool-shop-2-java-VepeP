package com.codecool.shop.model;

import java.util.HashMap;

public class Cart {
    private static HashMap<Product, Integer> shoppingCart = null;

    public Cart() {
    }

    public static HashMap<Product, Integer> getInstance() {
        if (shoppingCart == null) shoppingCart = new HashMap<>();
        return shoppingCart;
    }

    public void addToCart(Product product){
        if (checkIfProductInCart(product)) increaseProductQuantity(product);
        else shoppingCart.put(product, 1);
    }

    public void removeFromCart(Product product){
        shoppingCart.remove(product);
    }

    public void increaseProductQuantity(Product product) {
        Integer quantity = shoppingCart.get(product);
        if (quantity != null) shoppingCart.put(product, ++quantity);
    }

    private void decreaseProductQuantity(Product product) {
        Integer quantity = shoppingCart.get(product);
        if (quantity != null && quantity > 2) shoppingCart.put(product, --quantity);
        else if (quantity != null) removeFromCart(product);
    }

    private boolean checkIfProductInCart(Product product){
        for (Product myProduct : shoppingCart.keySet()) {
            if (myProduct.equals(product)) return true;
        }
        return false;
    }
}
