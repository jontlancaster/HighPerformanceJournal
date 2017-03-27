package com.journal.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
@Table(name="user_roles")
public class UserRole {
    @Id
    @Column(name="user_role_id")
    private int userRoleId;

    @Column(name="username")
    private String username;

    @Column(name = "role")
    private String role;
}