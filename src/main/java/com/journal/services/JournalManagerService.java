package com.journal.services;

import com.journal.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jonathon lancaster on 2/25/2017.
 */
public class JournalManagerService {

    @Autowired
    JournalRepository repository;

}
