package com.codecool.shop.datahandler;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/categories")
public class CategoryServlet extends HttpServlet {

    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ProductDao productDataStore = ProductDaoMem.getInstance();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        // ProductCategory category = productCategoryDataStore.find(id);

        CodecoolShopDbManager codecoolShopDbManager = new CodecoolShopDbManager();
        ProductCategoryDao productCategoryDao = codecoolShopDbManager.getProductCategoryDao();
        ProductCategory category = productCategoryDao.find(id);

        PrintWriter out = response.getWriter();
        // out.println(gson.toJson(productDataStore.getBy(category)));
        out.println(gson.toJson(codecoolShopDbManager.getProductDao().getBy(category)));
    }
}