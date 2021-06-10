package com.projectssm.controller;

import com.les.common.base.ResponseData;
import com.projectssm.model.Staff;
import com.projectssm.service.StaffService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@CrossOrigin

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("/staffquerylist")
    public List<Staff> staffquerylist(@RequestBody Staff staff){ return staffService.staffquerylist(staff); }


    @PostMapping("/staffquerylist2")
    public ResponseData staffquerylist2(){ return ResponseData.ok(staffService.staffquerylist2()); }

    @PostMapping("/staffquerylist3")
    public List<Staff> staffquerylist3(){ return staffService.staffquerylist2(); }

    @PostMapping("/staffquerylist4")
    public List<Staff> staffquerylist4(){ return staffService.staffquerylist3(); }

    @GetMapping("/querylistbyprojectid/{projectid}")
    public ResponseData querylistbyprojectid(@PathVariable(value = "projectid") String projectid){
        System.out.println(ResponseData.ok(staffService.querylistbyprojectid(projectid)).getData());
        return ResponseData.ok(staffService.querylistbyprojectid(projectid)); }

    /**
     * 通过id查询
     * @return
     */
    @GetMapping("/get-by-id/{id}")
    public Staff getById(@PathVariable(value = "id") String id){
        return staffService.getStaffById(id);
    }

    @PostMapping("/saveSpecia")
    public String add(@RequestBody Staff staff){
        int su = staffService.add(staff);
        if(su==0){
            return "新增失败";
        }
        return "新增成功";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id){
        int su = staffService.delete(id);
        if(su==0){
            return "删除失败";
        }
        return "删除成功";
    }

    @PostMapping("/update")
    public String update(@RequestBody Staff staff){
        int su = staffService.update(staff);
        if(su==0){
            return "更新失败";
        }
        return "更新成功";
    }

    @GetMapping(value="/staffprojectquerylist/{id}",consumes="application/json",produces="application/json")
    public String[] staffprojectquerylist(@PathVariable(value = "id") String id){
        return staffService.staffprojectquerylist(id);
    }




    @PostMapping("/updatestaff")
    public String updatestaff(@RequestBody List<Map> a){
        String projectid= (String) a.get(0).get("disabled");
//        System.out.println(a.get(0).get("disabled"));//打印传递的项目id

        for (int i = 0; i <a.size(); i++) {
            staffService.updatestaff((String) a.get(i).get("value"),projectid);
        }

    //if(a.get(0).get("checked")=="outstaff"){

    String str[]=new String[a.size()];
    for (int i = 0; i <a.size(); i++) {
        str[i]= (String) a.get(i).get("value");
    }
    staffService.updatestaff2(str,projectid);

    //}


        return "test";
    }

    @GetMapping("/updatestaff3/{id}")
    public String updatestaffstate(@PathVariable(value = "id") String id){
        int su = staffService.updatestaff3(id);
        if(su==0){
            return "恢复失败";
        }
        return "恢复成功";
    }
}