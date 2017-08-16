package com.journal.dto;

import lombok.Data;

@Data
public class SaveUserRequest {
    private String firstName;
    private String lastName;
    private boolean enabled;
    private String password;
    private String username;
}
