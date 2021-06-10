package com.projectssm.service;

import com.projectssm.mapper.ProjectMapper;
import com.projectssm.model.Project;
import com.projectssm.model.Staff;
import com.projectssm.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    public List<Project> querylist(Project project){
       return projectMapper.querylist(project);
    }

    public List<Project> querylist2(){
        return projectMapper.querylist2();
    }
    public List<Project> querylist3(String projectid){
        return projectMapper.querylist3(projectid);
    }

    public Project getProjectById(String id){return projectMapper.getProjectById(id);}

    public int add(Project project){return projectMapper.add(project);}

    public int delete(String id){return projectMapper.delete(id);}

    public int update(Project project){return projectMapper.update(project);}

    public int finish(Project project){return projectMapper.finish(project);}

    public int stop(Project project){return projectMapper.stop(project);}

    public int recovery(String id){return projectMapper.recovery(id);}

    public Map personnelproject(Project project){ return projectMapper.personnelproject(project);}
}
