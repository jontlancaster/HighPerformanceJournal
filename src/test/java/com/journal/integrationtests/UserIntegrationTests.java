package com.journal.integrationtests;

import com.journal.entities.User;
import com.journal.entities.UserType;
import com.journal.repositories.UserRepository;
import com.journal.repositories.UserTypeRepository;
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
    private UserTypeRepository userTypeRepository;

    private User user;
    private UserType userType;

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
    public void testSaveToUpdateExistingUser() {
        user = userRepository.findByUsername("jonlan");
        user.setFirstName("Jon");
        userRepository.save(user);
        userRepository.findByUsername("jonlan");
        assertEquals("Jon", user.getFirstName());
    }

    @Test
    public void testSaveToCreateNewUser() {
        if (userRepository.findByUsername("steve") != null) {
            user = userRepository.findByUsername("steve");
            userRepository.delete(user);
            assertEquals(userRepository.findByUsername("steve"), null);
        }
        user = new User();
        user.setFirstName("Steve");
        user.setLastName("Scuba");
        user.setUsername("steve");
        user.setPassword("passwd");
        user.setUserType(1);
        userRepository.save(user);
        userRepository.findByUsername("steve");
        assertEquals("Steve", user.getFirstName());
    }

    @Test
    public void testFindUserById() {
        user = userRepository.findByUserId(1);
        assertEquals("jonlan", user.getUsername());
    }

    @Test
    public void testFindUserTypeByUserTypeId() {
        userType = userTypeRepository.findByUserTypeId(1);
        assertEquals(1, userType.getUserTypeId());
    }
}
