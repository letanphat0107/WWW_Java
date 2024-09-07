/*
 * @ (#) LoginServlet.java     1.0   9/7/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.session_01.ex01.frontend.controller;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/7/2024
 * @version:  1.0
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.session_01.ex01.frontend.model.LoginBean;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(name = "LoginServlet", value = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").toString();
        String password = req.getParameter("password").toString();
        LoginBean loginBean = new LoginBean();
        boolean result = loginBean.validate(username, password);
        PrintWriter out = resp.getWriter();
        if (result) {
            out.println("Login success");
        } else {
            out.println("Login fail");
        }
    }
}
