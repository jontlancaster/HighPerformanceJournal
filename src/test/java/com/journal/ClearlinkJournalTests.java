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
public class ClearlinkJournalTests {

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
		List<Journal> journalList = journalRepository.findAllByUserId(1);
		assertEquals(2, journalList.size());
	}

	@Test
	public void testFindJournalEntryByJournalEntryId() {
		JournalEntry journalEntry = journalEntryRepository.findByJournalEntryId(1);
		assertEquals(1, journalEntry.getJournalEntryId());
	}

	@Test
	public void testFindAllJournalEntriesByJournalId() {
		List<JournalEntry> entryList = journalEntryRepository.findAllByJournalId(1);
		assertEquals(2, entryList.size());
	}
}
