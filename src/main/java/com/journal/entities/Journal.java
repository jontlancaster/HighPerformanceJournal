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
public class Journal {
    @Id
    private int journal_id;
    private String journal_name;
    private int user_id;
    private Timestamp created_date;
    private Timestamp modified_date;
}
