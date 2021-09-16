package com.codecool.shop.test;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Product product;
    HashMap<Product, Integer> cartContent;
    Cart cart;

    @BeforeEach
    void init() {
        product = Mockito.mock(Product.class);
        cart = new Cart();
        cartContent = Cart.getInstance();

    }

    @Test
    void increaseCartItemQuantityTest() {
        cart.addToCart(product);
        int cartSizeBefore = 1;
        cart.increaseProductQuantity(product);
        assertNotEquals(cartSizeBefore, cartContent.get(product));
    }

    @Test
    void increaseCartSizeWithNewItemTest() {
        cart.addToCart(product);
        Product product2 = Mockito.mock(Product.class);
        int cartSizeBefore = cartContent.size();
        cart.addToCart(product2);
        cart.increaseProductQuantity(product);
        assertNotEquals(cartSizeBefore, cartContent.size());
    }

    @Test
    void removeFromExistingProductFromCart() {
        cart.addToCart(product);
        int cartSizeBefore = cartContent.size();
        cart.removeFromCart(product);
        assertNotEquals(cartSizeBefore,cartContent.size());

    }
}