package com.journal.services;

import com.journal.dto.DateRangeFilter;
import com.journal.dto.JournalValuesInDateRange;
import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jonathon lancaster on 4/5/2017.
 */
@Service
public class JournalEntryManagerService {
    @Resource
    private JournalEntryRepository repository;
    @Resource
    private JournalManagerService journalManager;

    public JournalEntry loadTodaysEntry() {
        Long time = System.currentTimeMillis();
        Date today = new Date(time);

        return loadEntryByDate(today);
    }

    public JournalEntry loadEntryByDate(Date journalDate) {
        JournalEntry entry = new JournalEntry();

        try {
            entry = getJournalEntryByDate(journalDate);
        } catch (Exception e) {
            System.out.println("There was an error loading the entry for the date provided. " + e.getMessage());
        }

        return entry;
    }

    public JournalEntry saveEntry(JournalEntry entry) {
        Journal journal = journalManager.getJournalForLoggedInUser();
        JournalEntry updatedEntry = getJournalEntryByDate(entry.getCreatedDate());

        try {
            entry.setJournalId(journal.getJournalId());
            if (updatedEntry != null) {
                entry.setJournalEntryId(updatedEntry.getJournalEntryId());
                entry.setCreatedDate(updatedEntry.getCreatedDate());
            }
            updatedEntry = repository.save(entry);
        } catch (Exception e) {
            System.out.println("There was an error saving the journal entry " + e.getMessage());
        }

        return updatedEntry;
    }

    private JournalEntry getJournalEntryByDate(Date date) {
        JournalEntry entry = new JournalEntry();
        Journal journal = journalManager.getJournalForLoggedInUser();

        if (journal != null) {
            entry = repository.findByJournalIdAndCreatedDate(journal.getJournalId(), date);

            if (entry == null) {
                entry = createEmptyEntry(journal);
                entry = repository.save(entry);
            }
        }
        return entry;
    }

    private JournalEntry createEmptyEntry(Journal journal) {
        JournalEntry entry = new JournalEntry();
        entry.setJournalId(journal.getJournalId());
        entry.setPositiveReview("");
        entry.setGoal("");
        entry.setMomentum("");
        entry.setMentalToughness(1);
        entry.setWillingness(1);
        entry.setDetermination(1);
        entry.setMotivation(1);
        entry.setAttitude(1);
        entry.setPersonalImpact(1);

        return entry;
    }

    public JournalEntry findByCreatedDate(String createdDateString) {
        Journal journal = journalManager.getJournalForLoggedInUser();
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        try {
            java.util.Date createdDate = df.parse(createdDateString);
            return repository.findByJournalIdAndCreatedDate(journal.getJournalId(), new Date(createdDate.getTime()));
        } catch (ParseException e) {
            System.out.println("Unable to parse date: " + createdDateString + " for user "
                    + journal.getUser().getUsername() + " and journal id: " + journal.getJournalId());
        }
        return null;
    }

    public JournalValuesInDateRange getJournalValuesInDateRange(DateRangeFilter dateRangeFilter) {
        String pattern = "MM/dd/yyyy";
        DateTime startDateTime = getStringAsDateTime(dateRangeFilter.getStartDate(), pattern);
        DateTime endDateTime = getStringAsDateTime(dateRangeFilter.getEndDate(), pattern);

        Journal journal = journalManager.getJournalForLoggedInUser();
        //todo build the data from this list of entries
        List<JournalEntry> journalEntryList = repository.findByJournalIdWhereCreatedDateInRange(
                journal.getJournalId(), getSqlDateFromString(dateRangeFilter.getStartDate()),
                getSqlDateFromString(dateRangeFilter.getEndDate()));

        Days daysBetweenDateFromAndToDate = Days.daysBetween(startDateTime, endDateTime);
        //the key in this map is a category like willingness
        //the key in the value map is a date and the value is the rating
        Map<String, Map<String, Integer>> fieldValuesMap = new HashMap<>();
        Set<String> dates = new TreeSet<>();

        for (int count = 0; count <= daysBetweenDateFromAndToDate.getDays(); count++) {
            DateTime currentDateTime = startDateTime.plusDays(count);
            String date = getDateTimeAsString(currentDateTime, pattern);
            dates.add(date);

            addValueToMap("willingness", getDateWithRandomValue(date), fieldValuesMap);
            addValueToMap("motivation", getDateWithRandomValue(date), fieldValuesMap);
            addValueToMap("attitude", getDateWithRandomValue(date), fieldValuesMap);
            addValueToMap("determination", getDateWithRandomValue(date), fieldValuesMap);
            addValueToMap("mentalToughness", getDateWithRandomValue(date), fieldValuesMap);
        }

        return JournalValuesInDateRange.builder()
                .fields(fieldValuesMap.keySet())
                .dates(dates)
                .fieldValuesMap(fieldValuesMap)
                .build();
    }

    private void addValueToMap(String fieldName,
                               Map<String, Integer> dateValueMap,
                               Map<String, Map<String, Integer>> dateValuesMap) {
        Map<String, Integer> existingMap = dateValuesMap.get(fieldName);
        if (existingMap == null) {
            existingMap = new HashMap<>();
        }
        existingMap.putAll(dateValueMap);
        dateValuesMap.put(fieldName, existingMap);
    }

    private Map<String, Integer> getDateWithRandomValue(String date) {
        Map<String, Integer> dateValueMap = new HashMap<>();
        dateValueMap.put(date, generateRandomValue());
        return dateValueMap;
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
