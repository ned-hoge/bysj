package com.projectssm.model;

import lombok.Data;

@Data
public class RegUser {
    private String username;
    private String password;
    private String repass;
    private String staffid;
}
