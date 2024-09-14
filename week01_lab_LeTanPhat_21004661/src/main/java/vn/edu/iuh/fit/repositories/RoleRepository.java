/*
 * @ (#) RoleRepository.java     1.0   9/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/14/2024
 * @version:  1.0
 */
public class RoleRepository {
    EntityManager em;

    public RoleRepository() {
        this.em = Persistence.createEntityManagerFactory("MariaDB").createEntityManager();
    }

    /**
     * Get role name by account id
     * @param accountId
     * @return
     */
    public String getRoleNameById(String accountId) {
        EntityTransaction transaction = em.getTransaction();
        String mariadbGetRoleNameById = "SELECT role_id FROM grant_access WHERE account_id = ?";
        try{
            transaction.begin();
            String roleName = (String) em.createNativeQuery(mariadbGetRoleNameById)
                    .setParameter(1, accountId)
                    .getSingleResult();
            transaction.commit();
            return roleName;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }
}
