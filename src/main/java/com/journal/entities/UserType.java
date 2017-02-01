package com.journal.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
@Table(name="usertype")
public class UserType {
    @Id
    @Column(name="user_type_id")
    private int userTypeId;

    @Column(name="user_type")
    private String userType;
}
