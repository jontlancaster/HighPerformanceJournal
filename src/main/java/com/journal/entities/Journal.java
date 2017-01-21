package com.journal.entities;

import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import java.sql.Date;

/**
 */
@Entity
public class Journal {
    @Id
    private int journalId;
    private String journalName;
    private int userId;
    private Date createdDate;
    private Date modifiedDate;
}
