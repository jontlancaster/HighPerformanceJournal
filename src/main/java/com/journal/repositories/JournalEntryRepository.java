package com.journal.repositories;

import com.journal.entities.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by jonathon lancaster on 1/31/2017.
 */
@Repository
public interface JournalEntryRepository extends JpaRepository <JournalEntry, Integer> {
    JournalEntry findByJournalEntryId(int journalEntryId);

    List<JournalEntry> findAllByJournalId(int journalId);
}
