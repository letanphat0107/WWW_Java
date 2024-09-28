package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class Controllers extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");

            // Thêm sản phẩm


        } else if (action.equals("edit")) {
            // Sửa sản phẩm
        } else if (action.equals("delete")) {
            // Xóa sản phẩm
        }
    }
}
