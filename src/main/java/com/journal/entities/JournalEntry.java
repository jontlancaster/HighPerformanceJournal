package com.journal.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

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

    @CreationTimestamp
    @Column(name="created_date")
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name="modified_date")
    private Timestamp modifiedDate;
}
