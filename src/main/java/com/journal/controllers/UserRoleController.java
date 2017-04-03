package com.journal.controllers;

import com.journal.entities.UserRole;
import com.journal.repositories.UserRoleRepository;
import com.journal.services.UserRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@CrossOrigin()
@RestController
public class UserRoleController {

    @Autowired
    private UserRoleManagerService roleManager;

    @RequestMapping(value = "/userRoles/{username}")
    public List<UserRole> getUserRoles(@PathVariable("username") String username) { return roleManager.getRolesByUsername(username); }



    @RequestMapping(value = "/userRoles/enableAdmin", method = RequestMethod.POST)
    public boolean enableAdmin(@RequestBody String username) {
        return roleManager.enableAdmin(username);
    }

    @RequestMapping(value = "/userRoles/enableCoach", method = RequestMethod.POST)
    public boolean enableCoach(@RequestBody String username) {
        return roleManager.enableCoach(username);
    }

    @RequestMapping(value = "/userRoles/disableAdmin", method = RequestMethod.POST)
    public boolean disableAdmin(@RequestBody String username) {
        return roleManager.disableAdmin(username);
    }

    @RequestMapping(value = "/userRoles/disableCoach", method = RequestMethod.POST)
    public boolean disableCoach(@RequestBody String username) {
        return roleManager.disableCoach(username);
    }
}
