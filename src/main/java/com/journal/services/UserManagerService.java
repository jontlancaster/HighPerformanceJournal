package com.journal.services;

import com.journal.dto.SaveUserRequest;
import com.journal.dto.UserWithRoles;
import com.journal.entities.User;
import com.journal.entities.UserRole;
import com.journal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public User findUser(String username) {
        User user = new User();

        try {
            if (repository.countByUsername(username) != 0) {
                user = repository.findByUsername(username);
            }
        } catch (Exception exception) {
            System.out.println("Exception while finding user." + exception.getMessage());
        }

        return user;
    }

    public UserWithRoles getUserWithRoles(String username) {
        User user = findUser(username);
        if(user == null) {
            return null;
        }

        List<UserRole> roles = roleManager.getRolesByUsername(username);

        UserWithRoles userWithRoles = new UserWithRoles();
        userWithRoles.setFirstName(user.getFirstName());
        userWithRoles.setLastName(user.getLastName());
        userWithRoles.setEnabled(user.isEnabled());
        userWithRoles.setUsername(user.getUsername());
//        userWithRoles.setUserIsACoach(roles.stream().anyMatch(role -> role.getRole().equals()));//todo I left off here

        return userWithRoles;
    }

    public User createNewUser(User newUser) {
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setEnabled(true);

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
        if (user == null) {
            user = new User();
            System.out.println("Creating new user: " + saveUserRequest.getUsername());
        }
        user.setEnabled(saveUserRequest.isEnabled());
        user.setFirstName(saveUserRequest.getFirstName());
        user.setLastName(saveUserRequest.getLastName());
        user.setUsername(saveUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
        User savedUser = saveUser(user);

        if(savedUser == null) {
            return null;
        }

         if(saveUserRequest.isEnabled()) {
             boolean savedDefaultRole = roleManager.createDefaultRole(user.getUsername());
             if(!savedDefaultRole) {
                 System.err.println("Unable to save default role for user: " + user);
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
}
