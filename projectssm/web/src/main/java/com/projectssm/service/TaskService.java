package com.projectssm.service;

import com.projectssm.mapper.TaskMapper;
import com.projectssm.model.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    TaskMapper taskMapper;

    public List<Task> taskquerylist(Task task){
        return taskMapper.taskquerylist(task);
    }

    public List<Task> taskquerylist2(){
        return taskMapper.taskquerylist2();
    }


    public Task getTaskById(String id){return taskMapper.getTaskById(id);}

    public int add(Task task){return taskMapper.add(task);}

    public int delete(String id){return taskMapper.delete(id);}

    public int finish(String id){return taskMapper.finish(id);}

    public int finish2(String id){return taskMapper.finish2(id);}

    public int update(Task task){return taskMapper.update(task);}

    public Map selectcountlist(Task task){return taskMapper.selectcountlist(task);}

    public Map selectpersonnel(Task task){
        return taskMapper.selectpersonnel(task);}

    public Map selecttaskbyday(String string){
        return taskMapper.selecttaskbyday(string);}


    public Map selecttaskbydayandstaffid(String date,String staffid){
        return taskMapper.selecttaskbydayandstaffid(date,staffid);}

    public Map selecttaskbydayandstaffid2(String staffid){
        return taskMapper.selecttaskbydayandstaffid2(staffid);}

    public int update2(String date){return taskMapper.update2(date);}
}
