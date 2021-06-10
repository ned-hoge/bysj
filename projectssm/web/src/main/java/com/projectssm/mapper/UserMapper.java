package com.projectssm.mapper;

import com.projectssm.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author:dingji
 * @Date: 2021/4/23 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {

    User Sel(int id);

    User login(String userName,String passWord);

    int register(@Param("user")User user);

    User getstaffidbyuser(@Param("id") String id);

    int update(@Param("state") String state,@Param("staffid") String staffid);

    int updatepassword(@Param("password") String password,@Param("staffid") String staffid);
}