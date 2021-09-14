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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductServiceTest {
    ProductDao productDao;
    Product product;
    ProductCategoryDao productCategoryDao;
    ProductCategory productCategory;
    ProductService productService;
    Supplier supplier;


    @BeforeEach
    public void init() {
        productDao = ProductDaoMem.getInstance();
        product = mock(Product.class);
        supplier = mock(Supplier.class);
        productCategory = new ProductCategory(1,"Test","TestDB","Desc");
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        productService = new ProductService(productDao,productCategoryDao);
    }

    @Test
    void productDaoAddTest() {
        assertDoesNotThrow(() -> productDao.add(product));
    }

    @Test
    void productDaoGetAllTest() {
        productDao.add(product);
        assertFalse(productDao.getAll().isEmpty());
    }

    @Test
    void productCategoryGet() {
        productCategoryDao.add(productCategory);
        assertDoesNotThrow(()->productService.getProductCategory(1));
    }
    @Test
    void getProductsForCategoryTest(){
        Product noMockProduct = new Product(1,"Item",new BigDecimal("100"),"HUF","Desc",productCategory,supplier);
        productDao.add(noMockProduct);
        assertFalse(productService.getProductsForCategory(1).isEmpty());
    }

}