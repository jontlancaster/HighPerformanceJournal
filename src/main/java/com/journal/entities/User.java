package com.journal.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String username;

    private String password;

    @Column(name="user_type")
    private int userType;

    @Column(name="created_date", updatable = false, insertable = false)
    private Date createdDate;

    @Column(name="modified_date", updatable = false, insertable = false)
    private Date modifiedDate;

}
