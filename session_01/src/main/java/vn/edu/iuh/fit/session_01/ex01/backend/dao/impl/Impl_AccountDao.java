/*
 * @ (#) Impl_AccountDao.java     1.0   9/7/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.session_01.ex01.backend.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.session_01.ex01.backend.dao.Dao_Account;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/7/2024
 * @version:  1.0
 */
public class Impl_AccountDao implements Dao_Account {
    private EntityManager em;

    public Impl_AccountDao() {
        this.em = Persistence.createEntityManagerFactory("WWWJava_SQL").createEntityManager();
    }

    /**
     * Check login
     *
     * @param username username
     * @param password password
     * @return true if username and password are correct, otherwise false
     */
    @Override
    public boolean checkLogin(String username, String password) {
        EntityTransaction et = em.getTransaction();
        try{
            //DB have table account: username, password
            et.begin();
            String sql = "SELECT a.username FROM account a WHERE a.username = ? AND a.password = ?";
            boolean result = em.createNativeQuery(sql)
                    .setParameter(1, username)
                    .setParameter(2, password)
                    .getSingleResult() != null;
            et.commit();
            return result;
        }catch (Exception e){
            et.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
