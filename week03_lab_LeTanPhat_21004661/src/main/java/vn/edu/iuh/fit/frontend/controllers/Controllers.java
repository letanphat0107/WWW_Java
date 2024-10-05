package vn.edu.iuh.fit.frontend.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.backend.repositories.entities.Product;
import vn.edu.iuh.fit.frontend.models.ProductModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class Controllers extends HttpServlet {
    @Inject
    private ProductModel productModel;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String img = req.getParameter("img");

            productModel.createProduct(new Product(name, description, img));
        } else if (action.equals("edit")) {
            // Sửa sản phẩm
        } else if (action.equals("delete")) {
            // Xóa sản phẩm
        } else if (action.equals("products")) {
            List<Product> products = productModel.getProducts();
            req.setAttribute("products", products);
            req.getRequestDispatcher("products.jsp").forward(req, resp);
        }
    }
}
