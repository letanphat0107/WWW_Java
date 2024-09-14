/*
 * @ (#) RoleTest.java     1.0   9/14/2024
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
import vn.edu.iuh.fit.repositories.RoleRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleTest {
    private RoleRepository roleRepository;
    @BeforeAll
    public void setUp() {
        roleRepository = new RoleRepository();
    }

    @Test
    public void testGetRoleNameById() {
        String roleName = roleRepository.getRoleNameById("met");
        assertNotNull(roleName);
    }
}
