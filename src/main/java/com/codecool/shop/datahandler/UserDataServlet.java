package com.codecool.shop.datahandler;

import com.codecool.shop.service.FileHandler;

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

@WebServlet(name = "UserDataServlet", urlPatterns = "/user-data")
public class UserDataServlet extends HttpServlet {

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileHandler fileHandler = new FileHandler();
        int nextOrderId = fileHandler.getNextOrderID();
        fileHandler.saveFile(jsonString.toString(), fileHandler.getOrderFile(nextOrderId));

        PrintWriter out = response.getWriter();
        out.println("{\"id\": " + nextOrderId + "}");
    }
}
