package com.projectssm.controller;



import com.projectssm.model.Project;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController

public class VcodeController {//验证码快捷获取
    @RequestMapping("/dingji")
    public String dingji(HttpServletRequest request){
        return (String) request.getSession().getAttribute("accountLoginCpacha");
    }
    @RequestMapping("/sjkcs")
    public String a(){
        return "wc";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id){
        return id;
    }

    @PostMapping("/stop")
    public String stop(@RequestBody Project project){
        return project.getProjectname();
    }
}
