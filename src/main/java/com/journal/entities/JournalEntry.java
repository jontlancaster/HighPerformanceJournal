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
    private int journal_entry_id;
    private int journal_id;
    private String positive_review;
    private String goal;
    private String momentum;
    private int mental_toughness;
    private int willingness;
    private int determination;
    private int motivation;
    private int attitude;
    private int personal_impact;
    private Timestamp created_date;
    private Timestamp modified_date;


}
