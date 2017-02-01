package com.journal;

import com.journal.entities.Journal;
import com.journal.entities.UserType;
import com.journal.repositories.JournalRepository;
import com.journal.repositories.UserRepository;
import com.journal.entities.User;
import com.journal.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping("/users/{username}")
	public User user(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}

	@RequestMapping("/userTypes/{userType}")
	public UserType userType(@PathVariable("userType") String userType) { return userTypeRepository.findByUserType(userType); }

	@RequestMapping("/journals/{userJournals}")
	public List<Journal> numberOfJournals(@PathVariable("userJournals") int userId) { return journalRepository.findAllByUserId(userId); }


	public static void main(String[] args) {
		SpringApplication.run(ClearlinkJournal.class, args);
	}
}