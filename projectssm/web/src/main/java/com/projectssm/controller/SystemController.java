package com.projectssm.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projectssm.model.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/system")//验证码控制器
public class SystemController {

    /**
     * ��ϵͳ���е���֤������ô˷���
     * @param vcodeLen
     * @param width
     * @param height
     * @param cpachaType:����������֤������ͣ������ַ���
     * @param request
     * @param response
     */
    @RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
    public void generateCpacha(
            @RequestParam(name="vl",required=false,defaultValue="4") Integer vcodeLen,
            @RequestParam(name="w",required=false,defaultValue="100") Integer width,
            @RequestParam(name="h",required=false,defaultValue="30") Integer height,
            @RequestParam(name="type",required=true,defaultValue="loginCpacha") String cpachaType,
            HttpServletRequest request,
            HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(cpachaType, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
