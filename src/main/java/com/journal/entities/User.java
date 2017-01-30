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
public class User {
    @Id
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int userType;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

}
