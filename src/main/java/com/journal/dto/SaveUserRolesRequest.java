package com.journal.dto;

import lombok.Data;

@Data
public class SaveUserRolesRequest {
    private boolean userIsAnAdmin;
    private boolean userIsACoach;
    private String username;
}
