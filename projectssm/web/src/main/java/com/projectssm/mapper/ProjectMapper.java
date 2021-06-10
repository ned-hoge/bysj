package com.projectssm.mapper;

import com.projectssm.model.Project;
import com.projectssm.model.Staff;
import com.projectssm.model.Task;
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
public interface ProjectMapper {
    List<Project> querylist(@Param("project") Project project);
    List<Project> querylist2();
    List<Project> querylist3(@Param("projectid") String projectid);
    Project getProjectById(@Param("id") String id);
    int add(@Param("project") Project project);
    int delete(@Param("id") String id);
    int update(@Param("project")Project project);
    int finish(@Param("project")Project project);
    int stop(@Param("project")Project project);
    int recovery(@Param("id")String id);
    Map personnelproject(@Param("project") Project project);
}