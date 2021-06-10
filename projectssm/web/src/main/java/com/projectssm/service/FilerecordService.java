package com.projectssm.service;

import com.projectssm.mapper.FilerecordMapper;
import com.projectssm.model.FileRecord;
import com.projectssm.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FilerecordService {

    @Autowired
    FilerecordMapper filerecordMapper;

    public List<FileRecord> FILE_RECORDSquerylist(FileRecord fileRecord){
        return filerecordMapper.FILE_RECORDSquerylist(fileRecord);
    }
    
    public FileRecord getfilerecordById(String id){return filerecordMapper.getfilerecordById(id);}

    public int add(FileRecord fileRecord){return filerecordMapper.add(fileRecord);}

    public int delete(String id){return filerecordMapper.delete(id);}
    
}
