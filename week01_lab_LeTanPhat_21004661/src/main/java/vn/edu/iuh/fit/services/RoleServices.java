/*
 * @ (#) RoleServices.java     1.0   9/14/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.repositories.RoleRepository;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 9/14/2024
 * @version:  1.0
 */
public class RoleServices {
    private RoleRepository roleRepository;

    public RoleServices() {
        this.roleRepository = new RoleRepository();
    }

    public String getRole (String username) {
        return roleRepository.getRoleNameById(username);
    }
}
