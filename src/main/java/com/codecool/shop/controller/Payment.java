package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class Payment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CodecoolShopDbManager codecoolShopDbManager = new CodecoolShopDbManager();
        CartDao cartDao = codecoolShopDbManager.getCartDao();
        // CartDao cartDataStore = CartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        // context.setVariable("products", cartDataStore.getAll());
        // context.setVariable("totalPrice", cartDataStore.getTotalPrice().toString());

        context.setVariable("products", cartDao.getAll());
        context.setVariable("totalPrice", cartDao.getTotalPrice().toString());

        engine.process("payment/payment.html", context, resp.getWriter());
    }
}
