package com.codecool.shop.datahandler;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SupplierServlet", urlPatterns = "/suppliers")
public class SupplierServlet extends HttpServlet {

    SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        Supplier supplier = productSupplierDataStore.find(id);

        PrintWriter out = response.getWriter();

        out.println(supplier.getProducts());
    }
}
