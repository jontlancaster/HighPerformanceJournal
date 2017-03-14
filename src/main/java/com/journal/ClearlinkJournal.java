package com.journal;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.entities.UserType;
import com.journal.repositories.JournalEntryRepository;
import com.journal.repositories.JournalRepository;
import com.journal.repositories.UserRepository;
import com.journal.entities.User;
import com.journal.repositories.UserTypeRepository;
import com.journal.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class ClearlinkJournal {

	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private JournalEntryRepository journalEntryRepository;

	@RequestMapping("/userTypes/{userType}")
	public UserType userType(@PathVariable("userType") String userType) { return userTypeRepository.findByUserType(userType); }

	@RequestMapping("/journals/{userJournals}")
	public List<Journal> getUserJournalsByUserId(@PathVariable("userJournals") int userId) { return journalRepository.findByUserUserId(userId); }

	@RequestMapping("/journalsbyusername/{userJournals}")
	public List<Journal> getUserJournalsByUsername(@PathVariable("userJournals") String username) {return journalRepository.findByUserUsername(username);}

	@RequestMapping("/journalEntries/{entries}")
	public List<JournalEntry> getJournalEntries(@PathVariable("entries") int journalId) { return journalEntryRepository.findByJournalJournalId(journalId); }

	public static void main(String[] args) {
		SpringApplication.run(ClearlinkJournal.class, args);
	}
}
