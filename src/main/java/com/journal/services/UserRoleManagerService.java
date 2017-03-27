package com.journal.services;

import com.journal.entities.UserRole;
import com.journal.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JLancaster on 3/21/2017.
 */
@Service
public class UserRoleManagerService {

    @Autowired
    private UserRoleRepository repository;
    private final String admin = "ADMIN";
    private final String coach = "COACH";
    private final String user = "USER";

    public boolean createUserRoles(UserRole role) {
        boolean success = false;
        if (role.getRole().equals(admin)) {
            repository.save(role);
            success = true;
        }
        return success;
    }
}
