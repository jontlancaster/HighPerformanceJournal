package com.journal.controllers;

import com.journal.dto.SaveUserRolesRequest;
import com.journal.entities.UserRole;
import com.journal.services.UserRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/{username}", method = RequestMethod.GET)
    public List<UserRole> getUserRoles(@PathVariable("username") final String username) {
        return roleManager.getRolesByUsername(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/enableAdmin", method = RequestMethod.POST)
    public boolean enableAdmin(@RequestBody final String username) { return roleManager.enableAdmin(username); }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/enableCoach", method = RequestMethod.POST)
    public boolean enableCoach(@RequestBody final String username) {
        return roleManager.enableCoach(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/enableAdminAndCoach", method = RequestMethod.POST)
    public boolean enableAdminAndCoach(@RequestBody final String username) {
        boolean result = roleManager.enableAdmin(username);
        if(result == true)
            result = roleManager.enableCoach(username);

        return result;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/disableAdmin", method = RequestMethod.POST)
    public boolean disableAdmin(@RequestBody final String username) {
        return roleManager.disableAdmin(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/disableCoach", method = RequestMethod.POST)
    public boolean disableCoach(@RequestBody final String username) {
        return roleManager.disableCoach(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/disableAdminAndCoach", method = RequestMethod.POST)
    public boolean disableAdminAndCoach(@RequestBody final String username) {
        boolean result = roleManager.disableAdmin(username);
        if(result == true)
            result = roleManager.disableCoach(username);

        return result;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/saveUserRoles", method = RequestMethod.POST)
    public boolean saveUserRoles(@RequestBody SaveUserRolesRequest saveUserRolesRequest) {
        return roleManager.saveRolesForUser(saveUserRolesRequest);
    }
}
