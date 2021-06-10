package com.projectssm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:dingji
 * @Date: 2021/4/23 0026
 * @Time: 14:39
 */
@NoArgsConstructor
@AllArgsConstructor//生成全参构造
@Data
public class User {
    private String username;
    private String password;
    private String realName;
    private String staffid;
}