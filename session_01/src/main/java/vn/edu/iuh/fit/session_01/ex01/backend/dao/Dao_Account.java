/*
 * @ (#) Dao_Account.java     1.0   9/7/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.session_01.ex01.backend.dao;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/7/2024
 * @version:  1.0
 */
public interface Dao_Account {
    boolean checkLogin(String username, String password);
}
