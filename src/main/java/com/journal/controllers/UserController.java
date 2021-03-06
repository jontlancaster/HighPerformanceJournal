package com.journal.controllers;

import com.journal.dto.SaveUserRequest;
import com.journal.dto.UserWithRoles;
import com.journal.entities.User;
import com.journal.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JLancaster on 3/13/2017.
 */
@CrossOrigin()
@RestController
public class UserController {

    @Autowired
    private UserManagerService userManager;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/users/saveUser", method = RequestMethod.POST)
    public User saveUser(@RequestBody SaveUserRequest saveUserRequest) {
        return userManager.saveUser(saveUserRequest);
    }

    @RequestMapping(value="/users/createNewUser", method = RequestMethod.POST)
    public User createNewUser(@RequestBody SaveUserRequest saveUserRequest) {
        return userManager.createNewUser(saveUserRequest);
    }

    @PreAuthorize("hasRole('ROLE_COACH')")
    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
    public UserWithRoles findUser(@RequestParam("username") String username) { return userManager.getUserWithRoles(username); }

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

    @RequestMapping(value="/users/getUsernameOfLoggedInUser", method = RequestMethod.POST)
    public Map<String, String> getUsernameOfLoggedInUser() {
        Map<String, String> mapWithUsername = new HashMap<>();
        mapWithUsername.put("username", userManager.getUsernameOfLoggedInUser());
        return mapWithUsername;
    }
}
