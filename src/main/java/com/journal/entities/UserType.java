package com.journal.entities;

import org.springframework.data.annotation.Id;
import javax.persistence.Entity;

/**
 */
@Entity
public class UserType {
    @Id
    private int userTypeId;
    private String userType;
}
