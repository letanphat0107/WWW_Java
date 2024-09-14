/*
 * @ (#) ControllerServlet.java     1.0   9/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/14/2024
 * @version:  1.0
 */
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("login")) {
            
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
