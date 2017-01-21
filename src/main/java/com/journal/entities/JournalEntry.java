package com.journal.entities;

import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import java.sql.Date;

/**
 */
@Entity
public class JournalEntry {
    @Id
    private int journalEntryId;
    private int journalId;
    private String mainEntry;
    private String desiredOutcome;
    private String actionItem1;
    private String actionItem2;
    private String actionItem3;
    private String motivation;
    private String confidence;
    private String visualization;
    private String actionOrientation;
    private String decisionMaking;
    private Date createdDate;
    private Date modifiedDate;
}
