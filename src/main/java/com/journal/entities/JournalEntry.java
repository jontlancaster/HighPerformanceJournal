package com.journal.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
public class JournalEntry {
    @Id
    private int journalEntryId;
    private int journalId;
    private String positiveReview;
    private String goal;
    private String momentum;
    private int mentalToughness;
    private int willingness;
    private int determination;
    private int motivation;
    private int attitude;
    private int personalImpact;
    private Timestamp createdDate;
    private Timestamp modifiedDate;


}
