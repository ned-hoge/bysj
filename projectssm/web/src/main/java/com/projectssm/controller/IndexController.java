package com.projectssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制器
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(value = "/")
    public String index(){
        System.out.println("/index");
        return "/index/html/index.html";
    }
}
