package com.journal;

import com.journal.dao.UserRepository;
import com.journal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ClearlinkJournal {

	@Autowired
	private UserRepository repo;

	@RequestMapping("/users/{username}")
	public User user(@PathVariable("username") String username) {
		return repo.findByUsername(username);
	}

	public static void main(String[] args) {
		SpringApplication.run(ClearlinkJournal.class, args);
	}
}
