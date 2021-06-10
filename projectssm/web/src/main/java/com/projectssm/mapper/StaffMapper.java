package com.projectssm.mapper;

import com.projectssm.model.Project;
import com.projectssm.model.Staff;
import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:dingji
 * @Date: 2021/4/23 0026
 * @Time: 15:20
 */
@Repository
public interface StaffMapper {
    List<Staff> staffquerylist(@Param("staff") Staff staff);

    List<Staff> staffquerylist2();

    List<Staff> staffquerylist3();

    List<Staff> querylistbyprojectid(@Param("projectid") String projectid);

    Staff getStaffById(@Param("id") String id);

    int add(@Param("staff") Staff staff);

    int delete(@Param("id") String id);

    int update(@Param("staff") Staff staff);

    List<Map> staffprojectquerylist(@Param("id") String id);

    int updatestaff(@Param("id") String id, @Param("projectid") String projectid);

    int updatestaff2(@Param("str") String[] str, @Param("projectid") String projectid);

    int updatestaff3(@Param("id") String id);
}