package com.projectssm.controller;

import com.projectssm.model.Task;
import com.projectssm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@CrossOrigin

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/taskquerylist")
    public List<Task> staffquerylist(@RequestBody Task task){ return taskService.taskquerylist(task); }


    @PostMapping("/taskquerylist2")
    public List<Task> staffquerylist(){ return taskService.taskquerylist2(); }


    /**
     * 通过id查询
     * @return
     */
    @GetMapping("/get-by-id/{id}")
    public Task getById(@PathVariable(value = "id") String id){
        return taskService.getTaskById(id);
    }

    @PostMapping("/saveTask")
    public String add(@RequestBody Task task){
        int su = taskService.add(task);
        if(su==0){
            return "新增失败";
        }
        return "新增成功";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id){
        int su = taskService.delete(id);
        if(su==0){
            return "删除失败";
        }
        return "删除成功";
    }

    @GetMapping("/finish/{id}")
    public String finish(@PathVariable(value = "id") String id){
        int su = taskService.finish(id);
        if(su==0){
            return "完成失败";
        }
        return "完成成功";
    }

    @GetMapping("/finish2/{id}")
    public String finish2(@PathVariable(value = "id") String id){
        int su = taskService.finish2(id);
        if(su==0){
            return "逾期完成失败";
        }
        return "逾期完成成功";
    }

    @PostMapping("/update")
    public String update(@RequestBody Task task){
        int su = taskService.update(task);
        if(su==0){
            return "更新失败";
        }
        return "更新成功";
    }

    @PostMapping("/selectcountlist")
    public Map selectcountlist(@RequestBody Task task){
        return taskService.selectcountlist(task);
    }

    @PostMapping(value = "/selectpersonnel", produces = ("application/json;charset=utf-8"))
    public Map selectpersonnel(@RequestBody Task task){
        return taskService.selectpersonnel(task);
    }

    @GetMapping("/selecttaskbyday/{string}")
    public Map selecttaskbyday(@PathVariable(value = "string") String string){
       return taskService.selecttaskbyday(string);

    }

    @GetMapping("/selecttaskbydayandstaffid")
    public Map selecttaskbydayandstaffid(@RequestParam String date, @RequestParam  String staffid){
        return taskService.selecttaskbydayandstaffid(date,staffid);

    }

    @GetMapping("/selecttaskbydayandstaffid2/{staffid}")
    public Map selecttaskbydayandstaffid2(@PathVariable(value = "staffid") String staffid){
        return taskService.selecttaskbydayandstaffid2(staffid);

    }

    @GetMapping("/update2/{date}")
    public int update2(@PathVariable(value = "date") String date) throws ParseException {

//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.parse(date));
        return taskService.update2(date);

    }
}