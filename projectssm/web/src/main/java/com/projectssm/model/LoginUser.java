package com.projectssm.model;


import lombok.Data;

/**
 * @Author:dingji
 * @Date: 2021/4/23 0026
 * @Time: 14:39
 */
@Data

public class LoginUser {
    private String username;
    private String password;
    private String vercode;

}