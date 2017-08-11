package com.journal.controllers;

import com.journal.dto.DateRangeFilter;
import com.journal.dto.JournalValuesInDateRange;
import com.journal.entities.Journal;
import com.journal.repositories.JournalRepository;
import com.journal.services.JournalManagerService;
import org.joda.time.DateTime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Map;

/**
 * Created by JLancaster on 3/15/2017.
 */
@CrossOrigin()
@RestController
public class JournalController {
    @Resource
    private JournalRepository journalRepository;
    @Resource
    private JournalManagerService journalManager;


    @RequestMapping(value = "/journals/finByUserId",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public Journal getUserJournalByUserId(@RequestParam("userId") int userId) {
        return journalRepository.findByUserUserId(userId);
    }

    @RequestMapping(value = "/journals/findByUsername",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody Journal getUserJournalByUsername(@RequestParam("userName") String username) {
        return journalRepository.findByUserUsername(username);
    }

    @RequestMapping(value = "/journals/findByJournalId",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody Journal getJournalByJournalId(@RequestParam final int journalId) {
        return journalRepository.findByJournalId(journalId);
    }

    @RequestMapping(value = "/journals/findByLoggedInUser", method = RequestMethod.GET)
    public @ResponseBody Journal getJournalByLoggedInUser() {
        return journalManager.getJournalForLoggedInUser();
    }

    @RequestMapping(value = "/journals/getJournalValuesInDateRange", method = RequestMethod.POST)
    public JournalValuesInDateRange getJournalValuesInDateRange(@RequestBody DateRangeFilter dateRangeFilter) {
        return journalManager.getJournalValuesInDateRange(dateRangeFilter);
    }
}


