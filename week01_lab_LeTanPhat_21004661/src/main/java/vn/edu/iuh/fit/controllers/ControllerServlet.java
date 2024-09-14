/*
 * @ (#) ControllerServlet.java     1.0   9/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.services.AccountServices;
import vn.edu.iuh.fit.services.RoleServices;

import java.io.IOException;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/14/2024
 * @version:  1.0
 */
@WebServlet(name = "ControllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            AccountServices accountServices = new AccountServices();
            RoleServices roleServices = new RoleServices();

            Account result = accountServices.login(username, password);
            System.out.println(result);
            if (result != null) {
                req.getSession().setAttribute("account", result);
                if(roleServices.getRole(username).equals("admin"))
                    resp.sendRedirect("dashBoard.html");
                else
                    resp.sendRedirect("userMainPage.html");
            } else {
                resp.sendRedirect("login.html");
            }
        } else if (action.equals("register")) {

        } else if (action.equals("logout")) {

        } else if (action.equals("add")) {

        } else if (action.equals("edit")) {

        } else if (action.equals("delete")) {

        } else if (action.equals("search")) {

        } else if (action.equals("sort")) {

        }
    }
}
