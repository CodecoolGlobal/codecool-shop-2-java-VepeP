package com.codecool.shop.test;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    ProductService productService;
    ProductCategory productCategory;
    ProductCategoryDaoMem productCategoryDaoMem;
    ProductDaoMem productDaoMem;
    Product product;

    @BeforeEach
    void init() {
        productDaoMem = Mockito.mock(ProductDaoMem.class);
        productCategoryDaoMem = Mockito.mock(ProductCategoryDaoMem.class);
        productCategory = Mockito.mock(ProductCategory.class);
        product = Mockito.mock(Product.class);
        productService = new ProductService(productDaoMem, productCategoryDaoMem);
    }

    @Test
    void getProductCategoryNotNullTest() {
        when(productCategoryDaoMem.find(anyInt())).thenReturn(productCategory);
        assertNotNull(productService.getProductCategory(1));
    }

    @Test
    void getProductCategoryNullTest() {
        assertNull(productService.getProductCategory(0));
    }

    @Test
    void getProductsForCategoryNotNullListTest() {
        List<Product> p = new ArrayList<>();
        p.add(product);
        when(productCategoryDaoMem.find(anyInt())).thenReturn(productCategory);
        when(productDaoMem.getBy(productCategory)).thenReturn(p);
        assertFalse(productService.getProductsForCategory(1).isEmpty());
    }
    @Test
    void getProductsForCategoryNullListTest() {
        when(productCategoryDaoMem.find(anyInt())).thenReturn(productCategory);
        when(productDaoMem.getBy(productCategory)).thenReturn(new ArrayList<>());
        assertTrue(productService.getProductsForCategory(1).isEmpty());
    }

    }