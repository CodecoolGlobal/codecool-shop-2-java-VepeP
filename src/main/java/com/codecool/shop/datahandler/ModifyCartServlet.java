package com.codecool.shop.datahandler;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.FileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "modifyCartServlet",
        urlPatterns = {"/modifyCart"},
        initParams = {@WebInitParam(name = "id", value = ""),
                @WebInitParam(name = "amount", value = "")
        })
public class ModifyCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String amount = request.getParameter("amount");
        if (id != null && !id.equals("")) {
            int productID = Integer.parseInt(id);

            //CartDao cartDataStore = CartDaoMem.getInstance();


            CodecoolShopDbManager codecoolShopDbManager = new CodecoolShopDbManager();
            CartDao cartDao = codecoolShopDbManager.getCartDao();

            int actAmount = Integer.parseInt(amount);

            // cartDataStore.set(productID, actAmount);
            cartDao.set(productID, actAmount);

            // FileHandler fileHandler = new FileHandler();
            // fileHandler.saveFile(fileHandler.exportCartDao(), fileHandler.getCartFile());

        }
    }
}
