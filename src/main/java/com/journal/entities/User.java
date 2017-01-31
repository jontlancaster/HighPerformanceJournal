package com.journal.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private int user_type;
    private Timestamp created_date;
    private Timestamp modified_date;

}
