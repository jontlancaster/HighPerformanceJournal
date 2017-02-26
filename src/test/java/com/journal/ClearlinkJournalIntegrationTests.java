package com.journal;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.entities.UserType;
import com.journal.repositories.JournalEntryRepository;
import com.journal.repositories.JournalRepository;
import com.journal.repositories.UserRepository;
import com.journal.entities.User;
import com.journal.repositories.UserTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClearlinkJournalIntegrationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private JournalEntryRepository journalEntryRepository;

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
	public void testFindUserByUsernameThatIsNotInDB() {
		assertEquals(null, userRepository.findByUsername("unknown"));
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

	@Test
	public void testFindJournalByJournalId() {
		Journal journal = journalRepository.findByJournalId(1);
		assertEquals("my journal", journal.getJournalName());
	}

	@Test
	public void testFindAllJournalsByUserId() {
		List<Journal> journals = journalRepository.findByUserUserId(1);
		assertEquals(2, journals.size());
	}

	@Test
	public void testFindJournalsByUsername() {
		List<Journal> journals = journalRepository.findByUserUsername("jonlan");
		assertEquals(2, journals.size());
	}

	@Test
	public void testFindJournalEntryByJournalEntryId() {
		JournalEntry journalEntry = journalEntryRepository.findByJournalEntryId(1);
		assertEquals(1, journalEntry.getJournalEntryId());
	}

	@Test
	public void testFindAllJournalEntriesByJournalId() {
		List<JournalEntry> entryList = journalEntryRepository.findAllByJournal_JournalId(1);
		assertEquals(2, entryList.size());
	}
}
