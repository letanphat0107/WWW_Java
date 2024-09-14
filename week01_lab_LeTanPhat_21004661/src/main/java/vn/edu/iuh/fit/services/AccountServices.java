/*
 * @ (#) AccountServices.java     1.0   9/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/14/2024
 * @version:  1.0
 */
public class AccountServices {

    AccountRepository accountRepository;

    public AccountServices() {
        this.accountRepository = new AccountRepository();
    }
    public Account login(String username, String password) {
        Account account = accountRepository.login(username, password);
        if (account != null) {
            return account;
        }
        return null;
    }
}
