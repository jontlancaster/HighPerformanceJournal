package com.journal.services;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by jonathon lancaster on 4/5/2017.
 */
@Service
public class JournalEntryManagerService {
    @Autowired
    private JournalEntryRepository repository;
    @Resource
    private JournalManagerService journalManager;

    public JournalEntry loadTodaysEntry() {
        JournalEntry entry = new JournalEntry();
        Journal journal = journalManager.getJournalForLoggedInUser();
        Long time = System.currentTimeMillis() - 82300000;
        Date today = new Date(time);
        if (journal != null) {
            entry = repository.findByJournalAndCreatedDateAfter(journal, today);
        }

        return entry;
    }
}
