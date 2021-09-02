package com.codecool.shop.datahandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "UserDataServlet", urlPatterns = "/user-data")
public class UserDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonString = ""; // this is your data sent from client
        try {
            String line = "";
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jsonString += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
