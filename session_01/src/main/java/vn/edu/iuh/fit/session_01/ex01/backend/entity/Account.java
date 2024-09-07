/*
 * @ (#) Account.java     1.0   9/7/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.session_01.ex01.backend.entity;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/7/2024
 * @version:  1.0
 */
public class Account {
    private String username;
    private String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account username(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account password(String password) {
        this.password = password;
        return this;
    }

}
