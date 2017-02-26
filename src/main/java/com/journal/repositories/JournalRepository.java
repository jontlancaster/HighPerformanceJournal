package com.journal.repositories;

import com.journal.entities.Journal;
import com.journal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jonathon lancaster on 1/31/2017.
 */
@Repository
public interface JournalRepository extends JpaRepository <Journal, Integer> {
    Journal findByJournalId (int journalId);

    List<Journal> findByUserUserId(int userId);

    List<Journal> findByUserUsername(String username);
}

