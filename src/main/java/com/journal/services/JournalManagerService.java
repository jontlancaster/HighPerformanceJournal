package com.journal.services;

import com.journal.dto.DateRangeFilter;
import com.journal.dto.JournalValuesInDateRange;
import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.entities.User;
import com.journal.repositories.JournalRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

/**
 * Created by jonathon lancaster on 2/25/2017.
 */
@Service
public class JournalManagerService {
    @Resource
    private SecurityService security;
    @Autowired
    private JournalRepository repository;

    public Journal createJournal(User user) {
        Journal newJournal = new Journal();
        newJournal.setJournalName(user.getFirstName() + "\'s Journal");
        newJournal.setUser(user);
        try {
            newJournal = repository.save(newJournal);
        } catch (Exception exception) {
            System.out.println("There was an exception saving the new user. " + exception.getMessage());
        }
        return newJournal;
    }

    public Journal getJournalForLoggedInUser() {
        String username = security.getLoggedInUser();
        return getJournalByUsername(username);
    }

    public Journal getJournalByUsername(String username) {
        try {
            return repository.findByUserUsername(username);
        } catch (Exception e) {
            System.out.println("There was an error finding journal for username: " + username + " " + e.getMessage());
        }
        return null;
    }
}
