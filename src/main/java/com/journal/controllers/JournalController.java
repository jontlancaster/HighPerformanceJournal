package com.journal.controllers;

import com.journal.entities.Journal;
import com.journal.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@RestController
public class JournalController {
    @Autowired
    private JournalRepository journalRepository;

    @RequestMapping("/journals/findbyuserid/{userJournals}")
    public List<Journal> getUserJournalsByUserId(@PathVariable("userJournals") int userId) { return journalRepository.findByUserUserId(userId); }

    @RequestMapping("/journals/findbyusername/{userJournals}")
    public List<Journal> getUserJournalsByUsername(@PathVariable("userJournals") String username) {return journalRepository.findByUserUsername(username);}

    @RequestMapping("/journals/findByJournalId/{journalId}")
    public @ResponseBody Journal getJournalByJournalId(@PathVariable int journalId) {return journalRepository.findByJournalId(journalId);}
}


