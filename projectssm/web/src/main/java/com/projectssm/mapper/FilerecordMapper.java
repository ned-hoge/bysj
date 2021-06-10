package com.projectssm.mapper;

import com.projectssm.model.FileRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:dingji
 * @Date: 2021/4/23 0026
 * @Time: 15:20
 */
@Repository
public interface FilerecordMapper {
    List<FileRecord> FILE_RECORDSquerylist(@Param("fileRecord") FileRecord fileRecord);

    FileRecord getfilerecordById(@Param("id") String id);

    int add(@Param("fileRecord") FileRecord fileRecord);

    int delete(@Param("id") String id);
}