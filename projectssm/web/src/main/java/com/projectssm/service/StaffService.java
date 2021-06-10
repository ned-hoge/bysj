package com.projectssm.service;

import com.projectssm.mapper.ProjectMapper;
import com.projectssm.mapper.StaffMapper;
import com.projectssm.model.Project;
import com.projectssm.model.Staff;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StaffService {

    @Autowired
    StaffMapper staffMapper;

    public List<Staff> staffquerylist(Staff staff){
        return staffMapper.staffquerylist(staff);
    }

    public List<Staff> staffquerylist2(){
        return staffMapper.staffquerylist2();
    }

    public List<Staff> staffquerylist3(){
        return staffMapper.staffquerylist3();
    }

    public List<Staff> querylistbyprojectid(String projectid){
        return staffMapper.querylistbyprojectid(projectid);
    }

    public Staff getStaffById(String id){return staffMapper.getStaffById(id);}

    public int add(Staff staff){return staffMapper.add(staff);}

    public int delete(String id){return staffMapper.delete(id);}

    public int update(Staff staff){return staffMapper.update(staff);}

    public String[] staffprojectquerylist(String id){

        List<Map> thc=staffMapper.staffprojectquerylist(id);
        List<String> c = new ArrayList<>();
        for (int i = 0; i < thc.size(); i++) {
            c.add(thc.get(i).values().toString());
          //  System.out.println(c);
        }
        String []d=new String[thc.size()];
        for (int i = 0; i < thc.size(); i++) {
            d[i]=thc.get(i).values().toString().replace("[", "").replace("]", "");
        }
        return d;
    }

    public String updatestaff(String id,String projectid){  //传递员工id和项目id 把该员工的项目id改成传递的项目id参数
        int a=staffMapper.updatestaff(id,projectid);
        return "dingji";
    }

    public String updatestaff2(String[] str,String projectid){  //传递员工id和项目id 把该员工的项目id改成传递的项目id参数
        int a=staffMapper.updatestaff2(str,projectid);
        return "dingji";
    }


    public int updatestaff3(String id){  //传递员工id和项目id 把该员工的项目id改成传递的项目id参数
        return staffMapper.updatestaff3(id);
    }
}
