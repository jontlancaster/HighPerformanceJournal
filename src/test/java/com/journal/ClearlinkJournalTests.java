package com.journal;

import com.journal.entities.UserType;
import com.journal.repositories.UserRepository;
import com.journal.entities.User;
import com.journal.repositories.UserTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClearlinkJournalTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserTypeRepository userTypeRepository;

	private User user;
	private UserType userType;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testFindUserByUsername() {
		user = userRepository.findByUsername("jonlan");
		assertEquals("jonlan", user.getUsername());
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
