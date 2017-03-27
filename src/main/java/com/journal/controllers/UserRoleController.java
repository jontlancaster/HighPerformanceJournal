package com.journal.controllers;

import com.journal.entities.UserRole;
import com.journal.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@RestController
public class UserRoleController {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping("/userRoles/{username}")
    public List<UserRole> userType(@PathVariable("username") String username) { return userRoleRepository.findByUsername(username); }
}
