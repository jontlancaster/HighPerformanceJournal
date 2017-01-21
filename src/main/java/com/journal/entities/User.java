package com.journal.entities;

import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import java.sql.Date;


/**
 */
@Entity
public class User {
    @Id
    private int Id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int userType;
    private Date createdDate;
    private Date modifiedDate;
}
