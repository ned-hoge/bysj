package com.projectssm.model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FileRecord {
    private String id;
    private String filename;
    private String wordurl;
    private String pdfurl;
    private String projectid;
    private String staffid;
    @ApiModelProperty(value = "开始时间")
    @TableField("uploaddate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date uploaddate;

}
