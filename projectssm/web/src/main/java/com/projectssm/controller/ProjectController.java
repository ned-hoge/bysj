package com.projectssm.controller;

import com.les.common.base.ResponseData;
import com.projectssm.model.Project;
import com.projectssm.model.Staff;
import com.projectssm.model.Task;
import com.projectssm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/projectquerylist")
    public List<Project> querylist(@RequestBody Project project){

        return projectService.querylist(project);
    }

    @PostMapping("/projectquerylist2")
    public List<Project> querylist2(){

        return projectService.querylist2();
    }

    @PostMapping("/projectquerylist3")
    public ResponseData querylist3(){
        return ResponseData.ok(projectService.querylist2());
    }

    @GetMapping("/projectquerylist3/{projectid}")
    public ResponseData querylist4(@PathVariable(value = "projectid") String projectid){
        return ResponseData.ok(projectService.querylist3(projectid));
    }

    @GetMapping("/get-by-id/{id}")
    public Project getById(@PathVariable(value = "id") String id){
        return projectService.getProjectById(id);
    }

    @PostMapping("/saveProject")
    public String add(@RequestBody Project project){
        int su = projectService.add(project);
        if(su==0){
            return "新增失败";
        }
        return "新增成功";
    }

    @PostMapping("/update")
    public String update(@RequestBody Project project){
        int su = projectService.update(project);
        if(su==0){
            return "更新失败";
        }
        return "更新成功";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id){
        int su = projectService.delete(id);
        if(su==0){
            return "删除失败";
        }
        return "删除成功";
    }

    @PostMapping("/stop")
    public String stop(@RequestBody Project project){
        int su = projectService.stop(project);
        if(su==0){
            return "中止失败";
        }
        return "中止成功";
    }

    @PostMapping("/finish")
    public String finish(@RequestBody Project project){
        int su = projectService.finish(project);
        if(su==0){
            return "完成失败";
        }
        return "完成成功";
    }

    @ResponseBody
    @PostMapping("/recovery")
    public int recovery(@RequestBody String id){
        return projectService.recovery(id);
    }

    @PostMapping(value = "/personnelproject", produces = ("application/json;charset=utf-8"))
    public Map personnelproject(@RequestBody Project project){
        return projectService.personnelproject(project);
    }
}
