package com.journal.controllers;

import com.journal.entities.Journal;
import com.journal.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class JournalController {
    @Resource
    private JournalRepository journalRepository;

    @RequestMapping(value = "/journals/findbyuserid/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public List<Journal> getUserJournalsByUserId(@PathVariable("userId") int userId) {
        return journalRepository.findByUserUserId(userId);
    }

    @RequestMapping(value = "/journals/findbyusername/{userName}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody List<Journal> getUserJournalsByUsername(@PathVariable("userName") String username) {
        return journalRepository.findByUserUsername(username);
    }

    @RequestMapping(value = "/journals/findByJournalId/{journalId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody Journal getJournalByJournalId(@PathVariable int journalId) {
        return journalRepository.findByJournalId(journalId);
    }
}


