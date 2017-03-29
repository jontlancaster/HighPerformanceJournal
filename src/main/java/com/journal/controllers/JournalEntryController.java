package com.journal.controllers;

import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class JournalEntryController {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @RequestMapping("/journalEntries/{journalId}")
    public List<JournalEntry> getJournalEntries(@PathVariable("journalId") int journalId) { return journalEntryRepository.findByJournalJournalId(journalId); }

    @RequestMapping(value = "/journalEntries/create")
    public @ResponseBody JournalEntry createEntry(@RequestBody final JournalEntry newEntry) {
        return journalEntryRepository.save(newEntry);
    }

}
