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
    private int journalId;
    private String journalName;
    private int userId;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
}
