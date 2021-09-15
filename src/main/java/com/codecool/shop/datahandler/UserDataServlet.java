package com.codecool.shop.datahandler;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.manager.CodecoolShopDbManager;
import com.codecool.shop.service.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "UserDataServlet", urlPatterns = "/user-data")
public class UserDataServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserDataServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder jsonString = new StringBuilder(); // this is your data sent from client
        try {
            String line = "";
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jsonString.append(line);
            logger.info("Data (" + line + ") sent from client");
        } catch (Exception e) {
            logger.error("Could not send data from client!");
            e.printStackTrace();
        }

        FileHandler fileHandler = new FileHandler();
        int nextOrderId = fileHandler.getNextOrderID();

        CodecoolShopDbManager codecoolShopDbManager = new CodecoolShopDbManager();
        // OrderDao orderDao = codecoolShopDbManager.getOrderDao();

        fileHandler.saveFile(jsonString.toString(), fileHandler.getOrderFile(nextOrderId));

        PrintWriter out = response.getWriter();
        out.println("{\"id\": " + nextOrderId + "}");
    }
}
