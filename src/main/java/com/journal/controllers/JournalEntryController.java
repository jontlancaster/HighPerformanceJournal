package com.journal.controllers;

import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@RestController
public class JournalEntryController {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @RequestMapping("/journalEntries/{entries}")
    public List<JournalEntry> getJournalEntries(@PathVariable("entries") int journalId) { return journalEntryRepository.findByJournalJournalId(journalId); }

}
