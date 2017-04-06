package com.journal.controllers;

import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import com.journal.services.JournalEntryManagerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@CrossOrigin()
@RestController
public class JournalEntryController {
    @Resource
    private JournalEntryRepository journalEntryRepository;
    @Resource
    private JournalEntryManagerService entryManager;

    @RequestMapping("/journalEntries/{journalId}")
    public List<JournalEntry> getJournalEntries(@PathVariable("journalId") int journalId) { return journalEntryRepository.findByJournalJournalId(journalId); }

    @RequestMapping(value = "/journalEntries/create", method = RequestMethod.POST)
    public @ResponseBody JournalEntry createEntry(@RequestBody final JournalEntry newEntry) {
        return journalEntryRepository.save(newEntry);
    }

    @RequestMapping(value = "/journalEntries/todaysEntry", method = RequestMethod.GET)
    public JournalEntry getTodaysEntry() {
        return entryManager.loadTodaysEntry();
    }
}
