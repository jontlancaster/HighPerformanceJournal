package com.journal.integrationtests;

import com.journal.dto.SaveUserRequest;
import com.journal.entities.User;
import com.journal.entities.UserRole;
import com.journal.repositories.UserRepository;
import com.journal.repositories.UserRoleRepository;
import com.journal.services.UserManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathon lancaster on 2/25/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserIntegrationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserManagerService userManager;

    private User user;
    private UserRole userRole;

    @Test
    public void testFindUserByUsername() {
        user = userRepository.findByUsername("jonlan");
        assertEquals("jonlan", user.getUsername());
    }

    @Test
    public void testFindUserThatIsNotInDB() {
        assertEquals(0, userRepository.countByUsername("unknown"));
    }

    @Test
    public void testFindUserThatIsInDB() {
        assertEquals(1, userRepository.countByUsername("jonlan"));
    }

    @Test
    public void testUpdateExistingUser() {
        user = userRepository.findByUsername("jonlan");
        user.setFirstName("Jon");
        userManager.updateUser(user);
        userRepository.findByUsername("jonlan");
        assertEquals("Jon", user.getFirstName());
    }

    @Test
    public void testCreateNewUser() {
        if (userRepository.findByUsername("steve") != null) {
            user = userRepository.findByUsername("steve");
            userRepository.delete(user);
            assertEquals(userRepository.findByUsername("steve"), null);
        }
        SaveUserRequest saveUserRequest = new SaveUserRequest();
        saveUserRequest.setFirstName("Steve");
        saveUserRequest.setLastName("Scuba");
        saveUserRequest.setUsername("steve");
        saveUserRequest.setPassword("passwd");
        userManager.createNewUser(saveUserRequest);
        userRepository.findByUsername("steve");
        assertEquals("Steve", saveUserRequest.getFirstName());
    }

    @Test
    public void testFindUserTypeByUserTypeId() {
        userRole = userRoleRepository.findByUserRoleId(1);
        assertEquals(1, userRole.getUserRoleId());
    }
}
