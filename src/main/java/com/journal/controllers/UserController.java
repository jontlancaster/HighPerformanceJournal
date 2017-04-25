package com.journal.controllers;

import com.journal.entities.User;
import com.journal.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JLancaster on 3/13/2017.
 */
@CrossOrigin()
@RestController
public class UserController {

    @Autowired
    private UserManagerService userManager;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/users/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody final User newUser) {

        return userManager.createNewUser(newUser);
    }

    @PreAuthorize("hasRole('ROLE_COACH')")
    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
    public User findUser(@RequestParam("username") String username) { return userManager.findUser(username); }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/disable", method = RequestMethod.POST)
    public boolean disableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.disableUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/enable", method = RequestMethod.POST)
    public boolean enableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.enableUser(user);
    }
}
