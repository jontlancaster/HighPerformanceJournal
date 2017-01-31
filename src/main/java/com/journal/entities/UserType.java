package com.journal.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
public class UserType {
    @Id
    private int user_type_id;
    private String user_type;
}
