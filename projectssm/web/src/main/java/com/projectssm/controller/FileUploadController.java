package com.projectssm.controller;

import cn.hutool.core.util.URLUtil;
import com.les.common.utils.FileHandleUtil;
import com.les.common.utils.UuidUtil;
import com.projectssm.model.FileRecord;
import com.projectssm.service.FilerecordService;
import com.projectssm.service.ProjectService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class FileUploadController {
    @Autowired
    private DocumentConverter converter;  //用于转换
    @Autowired
    private FilerecordService filerecordService;
    /**
     * 附件上传
     *
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadPicture(@RequestParam("file") MultipartFile imgFile, @RequestParam String projectid,@RequestParam String staffid, HttpServletRequest request) throws FileNotFoundException {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", 1);
        String savePath = request.getSession().getServletContext().getRealPath("/") + "upload/file"
                + File.separatorChar;

        String saveUrl = request.getContextPath() + File.separatorChar + "upload/file" + File.separatorChar;
        System.out.println(savePath);

        if (imgFile == null) {
            result.put("message", "文件没接到");
            return result;
        }



        String size = String.valueOf(imgFile.getSize());
        String fileOriName = imgFile.getOriginalFilename();
        // 获取原名称
        String fileNowName = UuidUtil.getUuid() + "." + FilenameUtils.getExtension(fileOriName);
        // 生成唯一的名字
        try {
            FileHandleUtil.uploadSpringMVCFile(savePath,imgFile, fileNowName, projectid);
        } catch (Exception e) {
            return result;
        }
        result.put("error", 0);
        result.put("size", size);
        result.put("name", fileOriName);
        result.put("url", URLUtil.decode(saveUrl.replaceAll("\\\\", "/"))+fileNowName);
        //System.out.println("link=" + link + fileNowName);
//        System.out.println("url=" + url + fileNowName);


        String str1=savePath.replace("/","\\");
        String str2=savePath.replace("/","\\").replace("\\","\\\\");
        File file = new File(str2+fileNowName);//需要转换的文件
        try {
            File newFile = new File("C:\\Users\\Ricardo\\Desktop\\");//转换之后文件生成的地址
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            String savePath2=str1; //pdf文件生成保存的路径
            String fileName="JCccc"+ UUID.randomUUID().toString().replaceAll("-","").substring(0,6);
            String fileType=".pdf"; //pdf文件后缀
            String newFileMix=savePath2+fileName+fileType;  //将这三个拼接起来,就是我们最后生成文件保存的完整访问路径了

            System.out.println(result.get("url"));  //word文档url
            System.out.println("/upload/file/"+fileName+fileType);//转换后pdf文件地址
            Date d = new Date();
            System.out.println(d);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(d); //当前日期
            DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(dateNowStr);

            FileRecord fileRecord=new FileRecord();
            fileRecord.setFilename(fileOriName);
            fileRecord.setWordurl(result.get("url").toString());
            fileRecord.setPdfurl("/upload/file/"+fileName+fileType);
            fileRecord.setProjectid(projectid);
            fileRecord.setStaffid(staffid);
            fileRecord.setUploaddate(date);
            int a=filerecordService.add(fileRecord);
            //System.out.println(a);

            //文件转化
            converter.convert(file).to(new File(newFileMix)).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return result;
    }
}
