package com.journal.controllers;

import com.journal.entities.User;
import com.journal.repositories.UserRepository;
import com.journal.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JLancaster on 3/13/2017.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserManagerService userManager;

    @RequestMapping(value="users/create")
    public boolean createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                              @RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam("userType") int userType) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        return userManager.createNewUser(user);
    }

    @RequestMapping("users/find")
    public User findUser(@RequestParam("username") String username) { return userManager.findUser(username); }

    @RequestMapping("users/disable")
    public boolean disableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.disableUser(user);
    }

    @RequestMapping("users/enable")
    public boolean enableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.enableUser(user);
    }
}
