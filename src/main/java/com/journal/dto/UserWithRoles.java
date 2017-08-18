package com.journal.dto;

import lombok.Data;

@Data
public class UserWithRoles {
    private String firstName;
    private String lastName;
    private boolean userIsEnabled;
    private boolean userIsAnAdmin;
    private boolean userIsACoach;
    private String username;
}
