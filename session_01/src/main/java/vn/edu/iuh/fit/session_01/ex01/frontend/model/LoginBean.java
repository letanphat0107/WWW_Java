/*
 * @ (#) LoginBean.java     1.0   9/7/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.session_01.ex01.frontend.model;

import vn.edu.iuh.fit.session_01.ex01.backend.dao.impl.Impl_AccountDao;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/7/2024
 * @version:  1.0
 */
public class LoginBean {
    /**
     * Validate username and password
     *
     * @param username username
     * @param password password
     * @return true if username and password are correct, otherwise false
     */
    public boolean validate(String username, String password) {
        Impl_AccountDao accountDao = new Impl_AccountDao();
        return accountDao.checkLogin(username, password);
    }
}
