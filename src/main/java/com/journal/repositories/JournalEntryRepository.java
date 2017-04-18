package com.journal.repositories;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by jonathon lancaster on 1/31/2017.
 */
@Repository
public interface JournalEntryRepository extends JpaRepository <JournalEntry, Integer> {
    JournalEntry findByJournalEntryId(int journalEntryId);

    List<JournalEntry> findByJournalJournalId(int journalId);

    JournalEntry findByJournalAndCreatedDate(Journal journal, Date today);

    JournalEntry save(JournalEntry entry);

    void delete(JournalEntry entry);
}
