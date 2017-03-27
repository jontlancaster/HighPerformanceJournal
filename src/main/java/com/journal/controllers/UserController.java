package com.journal.controllers;

import com.journal.entities.User;
import com.journal.entities.UserRole;
import com.journal.services.UserManagerService;
import com.journal.services.UserRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JLancaster on 3/13/2017.
 */
@RestController
public class UserController {

    @Autowired
    private UserManagerService userManager;
    @Autowired
    private UserRoleManagerService userRoleManager;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="users/create")
    public boolean createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                              @RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam String userRole) {
        boolean status = false;
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        UserRole role = new UserRole();
        role.setUsername(username);
        role.setRole(userRole);
        if (userManager.createNewUser(user)) {
            status = userRoleManager.createUserRoles(role);
        }
        return status;
    }

    @RequestMapping(value = "users/find")
    public User findUser(@RequestParam("username") String username) { return userManager.findUser(username); }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "users/disable")
    public boolean disableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.disableUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "users/enable")
    public boolean enableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.enableUser(user);
    }
}
