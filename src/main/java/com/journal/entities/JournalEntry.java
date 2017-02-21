package com.journal.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
@Table(name="journalentry")
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="journal_entry_id")
    private int journalEntryId;

    @Column(name="journal_id")
    private int journalId;

    @Column(name="positive_review")
    private String positiveReview;

    private String goal;

    private String momentum;

    @Column(name="mental_toughness")
    private int mentalToughness;

    private int willingness;

    private int determination;

    private int motivation;

    private int attitude;

    @Column(name="personal_impact")
    private int personalImpact;

    @Column(name="created_date", updatable = false, insertable = false)
    private Date createdDate;

    @Column(name="modified_date", updatable = false, insertable = false)
    private Date modifiedDate;
}
