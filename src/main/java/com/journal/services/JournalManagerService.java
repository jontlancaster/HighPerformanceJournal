package com.journal.services;

import com.journal.entities.Journal;
import com.journal.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jonathon lancaster on 2/25/2017.
 */
@Service
public class JournalManagerService {

    @Autowired
    JournalRepository repository;

    public boolean createJournal(Journal newJournal) {
            boolean success = false;

            try {
                repository.save(newJournal);
                success = true;
            } catch (Exception exception) {
                System.out.println("There was an exception saving the new user. " + exception.getMessage());
            }
            return success;
    }

}
