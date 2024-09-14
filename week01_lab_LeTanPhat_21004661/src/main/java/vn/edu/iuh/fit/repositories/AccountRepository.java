/*
 * @ (#) AccountRepository.java     1.0   9/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.entities.Account;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/14/2024
 * @version:  1.0
 */
public class AccountRepository {
    protected EntityManager entityManager;

    public AccountRepository() {
        this.entityManager = Persistence.createEntityManagerFactory("MariaDB").createEntityManager();
    }


    /**
     * Add account to database
     * @param account
     * @return
     */
    public boolean addAccount(Account account) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    /**
     * Delete account from database
     * @param account
     * @return
     */
    public boolean deleteAccount(Account account) {
        String accountId = account.getAccountId();
        String mariadbDelete = "DELETE FROM account WHERE account_id = '" + accountId + "'";
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery(mariadbDelete).executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }


    /**
     * Update account in database
     * @param account
     * @return
     */
    public boolean updateAccount(Account account) {
        String accountId = account.getAccountId();
        String mariadbUpdate = "UPDATE account SET full_name = '" + account.getFullName()
                + "', password = '" + account.getPassword() + "', email = '" + account.getEmail()
                + "', phone = '" + account.getPhone() + "', status = '" + account.getStatus()
                + "' WHERE account_id = '" + accountId + "'";
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery(mariadbUpdate).executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }
}
