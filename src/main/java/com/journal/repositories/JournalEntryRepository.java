package com.journal.repositories;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by jonathon lancaster on 1/31/2017.
 */
@Repository
public interface JournalEntryRepository extends JpaRepository <JournalEntry, Integer> {
    JournalEntry findByJournalEntryId(long journalEntryId);

    List<JournalEntry> findByJournalId(long journalId);

    JournalEntry findByJournalIdAndCreatedDate(long journalId, Date createdDate);

    JournalEntry save(JournalEntry entry);

    @Query("select journalEntry from JournalEntry journalEntry " +
            "where journalEntry.journalId = :journalId " +
            "and journalEntry.createdDate >= :fromDate " +
            "and journalEntry.createdDate <= :toDate")
    List<JournalEntry> findByJournalIdWhereCreatedDateInRange(@Param("journalId") long journalId,
                                                              @Param("fromDate") Date fromDate,
                                                              @Param("toDate") Date toDate);
}
