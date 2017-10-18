package com.journal.services;

import com.journal.dto.SaveUserRequest;
import com.journal.dto.UserWithRoles;
import com.journal.entities.User;
import com.journal.entities.UserRole;
import com.journal.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by jonathon lancaster on 2/13/2017.
 */
@Service
public class UserManagerService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRoleManagerService roleManager;
    @Autowired
    private JournalManagerService journalManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityService securityService;

    public User findUser(String username) {
        try {
            if (repository.countByUsername(username) != 0) {
                return repository.findByUsername(username);
            }
        } catch (Exception exception) {
            System.out.println("Exception while finding user." + exception.getMessage());
        }

        return null;
    }

    public UserWithRoles getUserWithRoles(String username) {
        User user = findUser(username);
        if (user == null) {
            return null;
        }

        List<UserRole> roles = roleManager.getRolesByUsername(username);

        UserWithRoles userWithRoles = new UserWithRoles();
        userWithRoles.setFirstName(user.getFirstName());
        userWithRoles.setLastName(user.getLastName());
        userWithRoles.setUserIsEnabled(user.isEnabled());
        userWithRoles.setUsername(user.getUsername());
        userWithRoles.setUserIsACoach(roleManager.userIsACoach(roles));
        userWithRoles.setUserIsAnAdmin(roleManager.userIsAnAdmin(roles));

        return userWithRoles;
    }

    public User createNewUser(SaveUserRequest saveUserRequest) {
        User user = new User();
        user.setFirstName(saveUserRequest.getFirstName());
        user.setLastName(saveUserRequest.getLastName());
        user.setUsername(saveUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
        user.setEnabled(saveUserRequest.isEnabled());

        try {
            if (repository.countByUsername(user.getUsername()) != 0) {
                System.out.println("That username already exists! Please enter a different username.");
            } else {
                user = repository.save(user);
                roleManager.createDefaultRole(user.getUsername());
                journalManager.createJournal(user);
            }
        } catch (Exception exception) {
            System.out.println("There was an exception saving the new user. " + exception.getMessage());
        }
        return user;
    }

    public boolean updateUser(User user) {
        boolean success = false;
        try {
            repository.save(user);
            success = true;
        } catch (Exception exception) {
            System.out.println("There was an exception updating the user. " + exception.getMessage());
        }
        return success;
    }

    public boolean disableUser(User user) {
        boolean success = false;

        try {
            user.setEnabled(false);
            success = updateUser(user);
        } catch (Exception exception) {
            System.out.println("There was an exception disabling the user. " + exception.getMessage());
        }
        return success;
    }

    public boolean enableUser(User user) {
        boolean success = false;

        try {
            user.setEnabled(true);
            success = updateUser(user);
        } catch (Exception exception) {
            System.out.println("There was an exception enabling the user. " + exception.getMessage());
        }
        return success;
    }

    public User saveUser(SaveUserRequest saveUserRequest) {
        User user = repository.findByUsername(saveUserRequest.getUsername());
        User savedUser = new User();
        if (user == null) {
//            user = new User();
            logger.info("Creating new user: " + saveUserRequest.getUsername());
            createNewUser(saveUserRequest);
        }
        else {
            user.setEnabled(saveUserRequest.isEnabled());
            user.setFirstName(saveUserRequest.getFirstName());
            user.setLastName(saveUserRequest.getLastName());
            user.setUsername(saveUserRequest.getUsername());
            if (StringUtils.hasText(saveUserRequest.getPassword())) {
                user.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
            }

            savedUser = saveUser(user);

            if (savedUser == null) {
                return null;
            }

            if (saveUserRequest.isEnabled()) {
                boolean savedDefaultRole = roleManager.createDefaultRole(user.getUsername());
                if (!savedDefaultRole) {
                    logger.error("Unable to save default role for user: " + user);
                }
            }
        }
            return savedUser;

    }

    private User saveUser(User user) {
        try {
            return repository.save(user);
        } catch (Exception ex) {
            System.out.println("There was a problem saving user: " + user + "\n" + ex);
        }
        return null;
    }

    public String getUsernameOfLoggedInUser() {
        return securityService.getLoggedInUser();
    }
}
