package com.journal.controllers;

import com.journal.entities.User;
import com.journal.entities.UserRole;
import com.journal.repositories.UserRepository;
import com.journal.services.UserManagerService;
import com.journal.services.UserRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by JLancaster on 3/13/2017.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserManagerService userManager;
    @Autowired
    private UserRoleManagerService userRoleManager;

    @Resource
    private UserRepository userRepository;

    @RequestMapping(value="users/create", method = RequestMethod.POST)
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

    @RequestMapping(value = "/users/createWithDefaultRole",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUserWithDefaultRole(@RequestBody final User newUser) {
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setEnabled(true);
        UserRole role = new UserRole();
        role.setUsername(user.getUsername());
        role.setRole("user");
        userRoleManager.createUserRoles(role);

        return userRepository.save(user);
    }

    @RequestMapping(value = "/users/find")
    public User findUser(@RequestParam("username") String username) { return userManager.findUser(username); }

    @RequestMapping(value = "/users/disable", method = RequestMethod.POST)
    public boolean disableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.disableUser(user);
    }

    @RequestMapping(value = "/users/enable", method = RequestMethod.POST)
    public boolean enableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.enableUser(user);
    }
}
