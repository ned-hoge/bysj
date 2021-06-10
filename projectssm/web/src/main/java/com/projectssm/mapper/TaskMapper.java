package com.projectssm.mapper;

import com.projectssm.model.Staff;
import com.projectssm.model.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:dingji
 * @Date: 2021/4/23 0026
 * @Time: 15:20
 */
@Repository
public interface TaskMapper {
    List<Task> taskquerylist(@Param("task") Task task);

    List<Task> taskquerylist2();

    Task getTaskById(@Param("id") String id);

    int add(@Param("task") Task task);

    int delete(@Param("id") String id);

    int finish(@Param("id") String id);

    int finish2(@Param("id") String id);

    int update(@Param("task") Task task);

    Map selectcountlist(@Param("task") Task task);

    Map selectpersonnel(@Param("task") Task task);

    Map selecttaskbyday(@Param("date") String date);

    Map selecttaskbydayandstaffid(@Param("date") String date,@Param("staffid") String staffid);

    Map selecttaskbydayandstaffid2(@Param("staffid") String staffid);

    int update2(@Param("date") String date);
}