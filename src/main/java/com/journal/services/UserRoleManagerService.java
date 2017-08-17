package com.journal.services;

import com.journal.dto.SaveUserRolesRequest;
import com.journal.entities.UserRole;
import com.journal.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JLancaster on 3/21/2017.
 */
@Service
public class UserRoleManagerService {

    @Autowired
    private UserRoleRepository repository;

    private static final String standardRole = "ROLE_USER";
    private static final String coachRole = "ROLE_COACH";
    private static final String adminRole = "ROLE_ADMIN";

    public boolean createDefaultRole(String username) {
        if (repository.countByUsernameAndRole(username, standardRole) > 0) {
            System.out.println("User: " + username + " already has role: " + standardRole);
            return true;
        }
        UserRole role = new UserRole();
        role.setUsername(username);
        role.setRole(standardRole);

        return repository.save(role) != null;
    }

    private boolean saveNewRole(String username, String role) {
        UserRole userRole = new UserRole();
        userRole.setUsername(username);
        userRole.setRole(role);

        return repository.save(userRole) != null;
    }

    public List<UserRole> getRolesByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean enableAdmin(String username) {
        if (repository.countByUsernameAndRole(username, coachRole) == 0)
            enableCoach(username);
        return enableRole(username, adminRole);
    }

    public boolean enableCoach(String username) {
        return enableRole(username, coachRole);
    }

    public boolean disableAdmin(String username) {
        return disableRole(username, adminRole);
    }

    public boolean disableCoach(String username) {
        if (repository.countByUsernameAndRole(username, adminRole) != 0)
            disableAdmin(username);
        return disableRole(username, coachRole);
    }

    private boolean enableRole(String username, String role) {
        boolean success = false;
        try {
            if (repository.countByUsernameAndRole(username, role) != 0) {
                System.out.println("The user already has that right.");
                success = true;
            } else {
                UserRole newRole = new UserRole();
                newRole.setUsername(username);
                newRole.setRole(role);

                repository.save(newRole);
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Unable to save rights " + e.getMessage());
        }

        return success;
    }

    private boolean disableRole(String username, String role) {
        boolean success = false;
        try {
            if (repository.countByUsernameAndRole(username, role) == 0) {
                System.out.println("The user: " + username + " does not have that role: " + role);
                success = true;
            } else {
                repository.deleteUserRoleByUsernameAndRole(username, role);
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Unable to remove rights " + e.getMessage());
        }
        return success;
    }

    @Transactional
    public boolean saveRolesForUser(SaveUserRolesRequest saveUserRolesRequest) {
        boolean savedCoach = modifyRole(saveUserRolesRequest.isUserIsACoach(), coachRole, saveUserRolesRequest.getUsername());
        boolean savedAdmin = modifyRole(saveUserRolesRequest.isUserIsAnAdmin(), adminRole, saveUserRolesRequest.getUsername());

        return savedAdmin && savedCoach;
    }

    private boolean modifyRole(boolean userShouldHaveRole,
                               String roleToModify,
                               String username) {
        if (userShouldHaveRole) {
            return enableRole(username, roleToModify);
        } else {
            return disableRole(username, roleToModify);
        }
    }
}
