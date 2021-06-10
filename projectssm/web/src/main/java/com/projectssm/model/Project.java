package com.projectssm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Project {
    private String id;
    private String projectname;
    private String partya;
    private String state;

    @ApiModelProperty(value = "开始时间")
    @TableField("posttime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date posttime;

    @ApiModelProperty(value = "截止日期")
    @TableField("endtime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date endtime;

    private String stopreasion;
    private String requiresum;
    private String requirecomplete;
    private String requireing;

}
