package com.projectssm.model;

import com.sun.corba.se.impl.ior.ObjectAdapterIdNumber;
import lombok.Data;

@Data
public class Staff {
    private String id;
    private String name;
    private String post;
    private String telephone;
    private String major;
    private String mailbox;
    private String idnumber;
    private String title;
    private String workex;
    private String contact;
    private String remark;
    private String projectid;
    private String disabled;
}
