package com.codecool.shop.datahandler;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.FileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addToCartServlet", urlPatterns = {"/cart"})
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            int productID = Integer.parseInt(id);

            // CartDao cartDataStore = CartDaoMem.getInstance();
            // ProductDao productDataStore = ProductDaoMem.getInstance();
            // Product actProduct = productDataStore.find(productID);

            CodecoolShopDbManager codecoolShopDbManager = new CodecoolShopDbManager();
            CartDao cartDao = codecoolShopDbManager.getCartDao();
            Product actProduct = codecoolShopDbManager.getProductDao().find(productID);

            if (actProduct != null) {
                cartDao.add(productID);

                // FileHandler fileHandler = new FileHandler();
                // fileHandler.saveFile(fileHandler.exportCartDao(), fileHandler.getCartFile());
            }
        }
    }
}
