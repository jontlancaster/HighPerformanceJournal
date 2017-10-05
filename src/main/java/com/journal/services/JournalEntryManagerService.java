package com.journal.services;

import com.journal.dto.DateRangeFilter;
import com.journal.dto.JournalAveragesInDateRange;
import com.journal.dto.JournalValuesInDateRange;
import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

/**
 * Created by jonathon lancaster on 4/5/2017.
 */
@Service
public class JournalEntryManagerService {
    public static final String WILLINGNESS = "willingness";
    public static final String MOTIVATION = "motivation";
    public static final String ATTITUDE = "attitude";
    public static final String DETERMINATION = "determination";
    public static final String MENTAL_TOUGHNESS = "mentalToughness";
    public static final String PERSONAL_IMPACT = "personalImpact";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
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
        JournalEntry updatedEntry = getJournalEntryByDate(entry.getEntryDate());

        try {
            entry.setJournalId(journal.getJournalId());
            if (updatedEntry != null) {
                entry.setJournalEntryId(updatedEntry.getJournalEntryId());
                entry.setEntryDate(updatedEntry.getEntryDate());
                entry.setCreatedDate(updatedEntry.getCreatedDate());
            }
            updatedEntry = repository.save(entry);
        } catch (Exception e) {
            System.err.println("There was an error saving the journal entry " + ExceptionUtils.getMessage(e));
        }

        return updatedEntry;
    }

    public JournalEntry getJournalEntryByDate(Date entryDate) {
        JournalEntry entry = new JournalEntry();

        Long time = System.currentTimeMillis();
        Date today = new Date(time);

        if (entryDate.after(today)) {
            System.out.print("The entry date is after today, returning today's journal.");
            entryDate = today;
        }
        Journal journal = journalManager.getJournalForLoggedInUser();

        if (journal != null) {
            entry = repository.findByJournalIdAndEntryDate(journal.getJournalId(), entryDate);

            if (entry == null) {
                entry = createEmptyEntry(journal, entryDate);
                entry = repository.save(entry);
            }
        }
        return entry;
    }

    private JournalEntry createEmptyEntry(Journal journal, Date entryDate) {
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
        entry.setEntryDate(entryDate);

        return entry;
    }

    public JournalValuesInDateRange getJournalValuesInDateRange(DateRangeFilter dateRangeFilter) {
        //the key in this map is a category like willingness
        //the key in the value map is a date and the value is the rating
        Map<String, Map<String, Integer>> fieldValuesMap = new TreeMap<>();
        Set<String> dates = new TreeSet<>();

        Journal journal = journalManager.getJournalByUsername(dateRangeFilter.getUsername());
        List<JournalEntry> journalEntryList = repository.findByJournalIdWhereEntryDateInRange(
                journal.getJournalId(), getSqlDateFromString(dateRangeFilter.getStartDate()),
                getSqlDateFromString(dateRangeFilter.getEndDate()));


        journalEntryList
                .forEach(journalEntry -> {
                    String date = getDateTimeAsString(new DateTime(journalEntry.getEntryDate()), MM_DD_YYYY);
                    dates.add(date);

                    addValueToMap(WILLINGNESS, date, journalEntry.getWillingness(), fieldValuesMap);
                    addValueToMap(MOTIVATION, date, journalEntry.getMotivation(), fieldValuesMap);
                    addValueToMap(ATTITUDE, date, journalEntry.getAttitude(), fieldValuesMap);
                    addValueToMap(DETERMINATION, date, journalEntry.getDetermination(), fieldValuesMap);
                    addValueToMap(MENTAL_TOUGHNESS, date, journalEntry.getMentalToughness(), fieldValuesMap);
                    addValueToMap(PERSONAL_IMPACT, date, journalEntry.getPersonalImpact(), fieldValuesMap);
                });


        return JournalValuesInDateRange.builder()
                .fields(fieldValuesMap.keySet())
                .dates(dates)
                .fieldValuesMap(fieldValuesMap)
                .build();
    }

    public JournalAveragesInDateRange getJournalAveragesInDateRange(DateRangeFilter dateRangeFilter) {
        Journal journal = journalManager.getJournalForLoggedInUser();
        List<JournalEntry> journalEntryList = repository.findByJournalIdWhereEntryDateInRange(
                journal.getJournalId(), getSqlDateFromString(dateRangeFilter.getStartDate()),
                getSqlDateFromString(dateRangeFilter.getEndDate()));

        return JournalAveragesInDateRange.builder()
                .attitude(journalEntryList.stream()
                        .mapToInt(JournalEntry::getAttitude)
                        .sum() / journalEntryList.size())
                .determination(journalEntryList.stream()
                        .mapToInt(JournalEntry::getDetermination)
                        .sum() / journalEntryList.size())
                .mentalToughness(journalEntryList.stream()
                        .mapToInt(JournalEntry::getMentalToughness)
                        .sum() / journalEntryList.size())
                .motivation(journalEntryList.stream()
                        .mapToInt(JournalEntry::getMotivation)
                        .sum() / journalEntryList.size())
                .personalImpact(journalEntryList.stream()
                        .mapToInt(JournalEntry::getPersonalImpact)
                        .sum() / journalEntryList.size())
                .willingness(journalEntryList.stream()
                        .mapToInt(JournalEntry::getWillingness)
                        .sum() / journalEntryList.size())
                .build();
    }

    private void addValueToMap(String fieldName,
                               String date,
                               Integer value,
                               Map<String, Map<String, Integer>> dateValuesMap) {
        Map<String, Integer> existingMap = dateValuesMap.get(fieldName);
        if (existingMap == null) {
            existingMap = new HashMap<>();
        }
        existingMap.put(date, value);
        dateValuesMap.put(fieldName, existingMap);
    }

    private Map<String, Integer> getDateWithRandomValue(String date) {
        Map<String, Integer> dateValueMap = new HashMap<>();
        dateValueMap.put(date, generateRandomValue());
        return dateValueMap;
    }

    private Map<String, Integer> getDateValueMap(String date, Integer value) {
        Map<String, Integer> dateValueMap = new HashMap<>();
        dateValueMap.put(date, value);
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
        return new Date(getStringAsDateTime(dateString, MM_DD_YYYY).getMillis());
    }
}
