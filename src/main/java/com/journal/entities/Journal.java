package com.journal.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
@Table(name="journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="journal_id")
    private int journalId;

    @Column(name="journal_name")
    private String journalName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="created_date", updatable = false, insertable = false)
    private Date createdDate;

    @Column(name="modified_date", updatable = false, insertable = false)
    private Date modifiedDate;
}
