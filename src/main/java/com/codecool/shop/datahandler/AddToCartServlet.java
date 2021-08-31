package com.codecool.shop.datahandler;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;

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

            CartDao cartDataStore = CartDaoMem.getInstance();
            Product actProduct = cartDataStore.find(productID);

            if (actProduct != null) cartDataStore.add(actProduct);
        }
    }
}
