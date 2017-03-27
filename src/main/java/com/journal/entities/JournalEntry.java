package com.journal.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
@Table(name="journal_entries")
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="journal_entry_id")
    private int journalEntryId;

    @ManyToOne
    @JoinColumn(name="journal_id")
    private Journal journal;

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
