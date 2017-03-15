package com.journal.controllers;

import com.journal.entities.UserType;
import com.journal.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JLancaster on 3/15/2017.
 */
@RestController
public class UserTypeController {
    @Autowired
    private UserTypeRepository userTypeRepository;

    @RequestMapping("/userTypes/{userType}")
    public UserType userType(@PathVariable("userType") String userType) { return userTypeRepository.findByUserType(userType); }
}
