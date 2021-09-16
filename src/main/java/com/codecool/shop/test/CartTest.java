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
    void addItemToCartTest(){
        int numberOfItemInCar = cartContent.size();
        cart.addToCart(product);
        assertNotEquals(numberOfItemInCar,cartContent.size());
    }

    @Test
    void increaseQuantityTest(){
        cart.addToCart(product);
        int cartSizeBefore = cartContent.get(product);
        cart.addToCart(product);
        assertNotEquals(cartSizeBefore,cartContent.get(product));
    }


    @Test
    void removeFromExistingProductFromCartTest() {
        cart.addToCart(product);
        int cartSizeBefore = cartContent.size();
        cart.removeFromCart(product);
        assertNotEquals(cartSizeBefore,cartContent.size());

    }
    @Test
    void decreaseQuantityFromExistingProductTest(){
        cart.addToCart(product);
        cart.addToCart(product);
        int cartItemQuantityBefore= cartContent.get(product);
        cart.removeFromCart(product);
        assertNotEquals(cartItemQuantityBefore,cartContent.get(product));
    }

}