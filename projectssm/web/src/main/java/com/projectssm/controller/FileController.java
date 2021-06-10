package com.projectssm.controller;

import com.les.common.base.ResponseData;
import com.projectssm.model.FileRecord;
import com.projectssm.model.Staff;
import com.projectssm.service.FilerecordService;
import com.projectssm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin

@RestController
@RequestMapping("/filerecord")
public class FileController {
    @Autowired
    private FilerecordService filerecordService;

    @PostMapping("/FILE_RECORDSquerylist")
    public List<FileRecord> FILE_RECORDSquerylist(@RequestBody FileRecord fileRecord){ return filerecordService.FILE_RECORDSquerylist(fileRecord); }

    /**
     * 通过id查询
     * @return
     */
    @GetMapping("/get-by-id/{id}")
    public FileRecord getfilerecordById(@PathVariable(value = "id") String id){
        return filerecordService.getfilerecordById(id);
    }

    @PostMapping("/saveSpecia")
    public String add(@RequestBody FileRecord fileRecord){
        int su = filerecordService.add(fileRecord);
        if(su==0){
            return "新增失败";
        }
        return "新增成功";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") String id){
        int su = filerecordService.delete(id);
        if(su==0){
            return "删除失败";
        }
        return "删除成功";
    }
}