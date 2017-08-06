package com.journal.services;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * Created by jonathon lancaster on 4/5/2017.
 */
@Service
public class JournalEntryManagerService {
    @Resource
    private JournalEntryRepository repository;
    @Resource
    private JournalManagerService journalManager;

    public JournalEntry loadTodaysEntry() {
        Long time = System.currentTimeMillis();
        Date today = new Date(time);

        return loadEntryByDate(today);
    }

    public JournalEntry loadEntryByDate(Date journalDate) {
        JournalEntry entry = new JournalEntry();

        try {
            entry = getJournalEntryByDate(journalDate);
        } catch (Exception e) {
            System.out.println("There was an error loading the entry for the date provided. " + e.getMessage());
        }

        return entry;
    }

    public JournalEntry saveEntry(JournalEntry entry) {
        Journal journal = journalManager.getJournalForLoggedInUser();
        JournalEntry updatedEntry = getJournalEntryByDate(entry.getCreatedDate());

        try {
            entry.setJournalId(journal.getJournalId());
            if (updatedEntry != null) {
                entry.setJournalEntryId(updatedEntry.getJournalEntryId());
                entry.setCreatedDate(updatedEntry.getCreatedDate());
            }
            updatedEntry = repository.save(entry);
        } catch (Exception e) {
            System.out.println("There was an error saving the journal entry " + e.getMessage());
        }

        return updatedEntry;
    }

    private JournalEntry getJournalEntryByDate(Date date) {
        JournalEntry entry = new JournalEntry();
        Journal journal = journalManager.getJournalForLoggedInUser();

        if (journal != null) {
            entry = repository.findByJournalIdAndCreatedDate(journal.getJournalId(), date);

            if (entry == null) {
                entry = createEmptyEntry(journal);
                entry = repository.save(entry);
            }
        }
        return entry;
    }

    private JournalEntry createEmptyEntry(Journal journal) {
        JournalEntry entry = new JournalEntry();
        entry.setJournalId(journal.getJournalId());
        entry.setPositiveReview("");
        entry.setGoal("");
        entry.setMomentum("");
        entry.setMentalToughness(1);
        entry.setWillingness(1);
        entry.setDetermination(1);
        entry.setMotivation(1);
        entry.setAttitude(1);
        entry.setPersonalImpact(1);

        return entry;
    }
}
