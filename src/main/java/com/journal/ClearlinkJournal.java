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
	private UserRepository userRepository;
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	@Autowired
	private UserManagerService userManager;

	@RequestMapping("/users/{username}")
	public User user(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}

	@RequestMapping("/userTypes/{userType}")
	public UserType userType(@PathVariable("userType") String userType) { return userTypeRepository.findByUserType(userType); }

	@RequestMapping("/journals/{userJournals}")
	public List<Journal> getUserJournalsByUserId(@PathVariable("userJournals") int userId) { return journalRepository.findByUserUserId(userId); }

	@RequestMapping("/journalsbyusername/{userJournals}")
	public List<Journal> getUserJournalsByUsername(@PathVariable("userJournals") String username) {return journalRepository.findByUserUsername(username);}

	@RequestMapping("/journalEntries/{entries}")
	public List<JournalEntry> getJournalEntries(@PathVariable("entries") int journalId) { return journalEntryRepository.findByJournalJournalId(journalId); }

	@RequestMapping("users/find")
	public User findUser(@RequestParam("username") String username) { return userManager.findUser(username); }

	@RequestMapping("users/disable")
	public boolean disableUser(@RequestParam("username") String username) {
		User user = userRepository.findByUsername(username);
		return userManager.disableUser(user);
	}

	@RequestMapping("users/enable")
	public boolean enableUser(@RequestParam("username") String username) {
		User user = userRepository.findByUsername(username);
		return userManager.enableUser(user);
	}

	@RequestMapping(value="users/create")
	public boolean createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
							  @RequestParam("username") String username, @RequestParam("password") String password,
							  @RequestParam("userType") int userType) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setUserType(userType);
		return userManager.createNewUser(user);
	}

	public static void main(String[] args) {
		SpringApplication.run(ClearlinkJournal.class, args);
	}
}
