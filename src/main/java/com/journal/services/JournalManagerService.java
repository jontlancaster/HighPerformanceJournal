package com.journal.services;

import com.journal.dto.DateRangeFilter;
import com.journal.dto.JournalValuesInDateRange;
import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.entities.User;
import com.journal.repositories.JournalRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by jonathon lancaster on 2/25/2017.
 */
@Service
public class JournalManagerService {
    @Resource
    private SecurityService security;
    @Autowired
    private JournalRepository repository;

    public Journal createJournal(User user) {
        Journal newJournal = new Journal();
        newJournal.setJournalName(user.getFirstName() + "\'s Journal");
        newJournal.setUser(user);
        try {
            newJournal = repository.save(newJournal);
        } catch (Exception exception) {
            System.out.println("There was an exception saving the new user. " + exception.getMessage());
        }
        return newJournal;
    }

    public Journal getJournalForLoggedInUser() {
        Journal journal = new Journal();
        String username = security.getLoggedInUser();

        try {
            journal = repository.findByUserUsername(username);
        } catch (Exception e) {
            System.out.println("There was an error finding the logged in user's journal." + e.getMessage());
        }

        return journal;
    }

    public JournalValuesInDateRange getJournalValuesInDateRange(DateRangeFilter dateRangeFilter) {
        String pattern = "MM/dd/yyyy";
        DateTime startDateTime = getStringAsDateTime(dateRangeFilter.getStartDate(), pattern);
        DateTime endDateTime = getStringAsDateTime(dateRangeFilter.getEndDate(), pattern);

        Days daysBetweenDateFromAndToDate = Days.daysBetween(startDateTime, endDateTime);
        Map<String, Map<String, Integer>> dateValuesMap = new HashMap<>();

        for (int count = 0; count <= daysBetweenDateFromAndToDate.getDays(); count++) {
            DateTime currentDateTime = startDateTime.plusDays(count);
            String date = getDateTimeAsString(currentDateTime, pattern);

            Map<String, Integer> entryValueMap = buildEntryValueMap();
            dateValuesMap.put(date, entryValueMap);
        }

        return JournalValuesInDateRange.builder()
                .dateValuesMap(dateValuesMap)
                .dates(dateValuesMap.keySet())
                .build();
    }

    private Map<String, Integer> buildEntryValueMap() {
        Map<String, Integer> entryValueMap = new HashMap<>();
        entryValueMap.put("willingness", generateRandomValue());
        entryValueMap.put("motivation", generateRandomValue());
        entryValueMap.put("attitude", generateRandomValue());
        entryValueMap.put("determination", generateRandomValue());
        entryValueMap.put("mentalToughness", generateRandomValue());

        return entryValueMap;
    }

    private Integer generateRandomValue() {
        return (int) (Math.random() * ((5) + 1));
    }

    private static DateTime getStringAsDateTime(final String dateString, final String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return dateTimeFormatter.parseDateTime(dateString);
    }

    public static String getDateTimeAsString(final DateTime dateTime, final String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return formatter.print(dateTime);
    }

    private Date getSqlDateFromString(String dateString) {
        return new Date(new DateTime(dateString).getMillis());
    }
}
