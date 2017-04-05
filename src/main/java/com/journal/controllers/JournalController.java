package com.journal.controllers;

import com.journal.entities.Journal;
import com.journal.repositories.JournalRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by JLancaster on 3/15/2017.
 */
@CrossOrigin()
@RestController
public class JournalController {
    @Resource
    private JournalRepository journalRepository;

    @RequestMapping(value = "/journals/finByUserId/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public Journal getUserJournalByUserId(@RequestParam("userId") int userId) {
        return journalRepository.findByUserUserId(userId);
    }

    @RequestMapping(value = "/journals/findByUsername/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody Journal getUserJournalByUsername(@RequestParam("userName") String username) {
        return journalRepository.findByUserUsername(username);
    }

    @RequestMapping(value = "/journals/findByJournalId/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody Journal getJournalByJournalId(@RequestParam int journalId) {
        return journalRepository.findByJournalId(journalId);
    }
}


