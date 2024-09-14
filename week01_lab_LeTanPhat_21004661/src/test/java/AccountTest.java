/*
 * @ (#) AccountTest.java     1.0   9/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/14/2024
 * @version:  1.0
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountTest {
    private AccountRepository accountRepository;
    @BeforeAll
    public void setUp() {
        accountRepository = new AccountRepository();
    }

    @Test
    public void testAddAccount() {
        Account account = new Account();
        account.setAccountId("21004661");
        account.setFullName("Le Tan Phat");
        account.setPassword("123456");
        account.setEmail("mail");
        account.setPhone("0123456789");
        account.setStatus((byte) 1);
        assertTrue(accountRepository.addAccount(account));
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account();
        account.setAccountId("21004661");
        assertTrue(accountRepository.deleteAccount(account));
    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account();
        account.setAccountId("21004661");
        account.setFullName("Le Tan Phat updated");
        account.setPassword("123456");
        account.setEmail("mail");
        account.setPhone("0123456789");
        account.setStatus((byte) 1);
        assertTrue(accountRepository.updateAccount(account));
    }

    @Test
    public void testLogin() {
        assertNotNull(accountRepository.login("met", "123"));
    }
}
