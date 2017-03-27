package com.journal.services;

import com.journal.entities.User;
import com.journal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jonathon lancaster on 2/13/2017.
 */
@Service
public class UserManagerService {

    @Autowired
    private UserRepository repository;

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

    public boolean createNewUser(User newUser) {
        boolean success = false;
        try {
            if (repository.countByUsername(newUser.getUsername()) != 0) {
                System.out.println("That username already exists! Please enter a different username.");
            } else {
                repository.save(newUser);
                success = true;
            }
        } catch (Exception exception) {
            System.out.println("There was an exception saving the new user. " + exception.getMessage());
        }
        return success;
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
}
