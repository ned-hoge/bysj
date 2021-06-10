package com.projectssm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Task {
    private String id;
    private String projectid;
    private String staffid;
    private String remark;
    private String state;

    @ApiModelProperty(value = "任务完成日期")
    @TableField("date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date date;



}
