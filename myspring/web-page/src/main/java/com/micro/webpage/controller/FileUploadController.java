package com.micro.webpage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//RestController  返回页面内容
@Controller //返回到指定页面
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

   // @PostMapping("/upload")
    @RequestMapping(value="/upload", method= RequestMethod.POST )
    public String fileUpload(MultipartFile uploadFile, HttpServletRequest request)  {
    String realPath=  request.getSession().getServletContext().getRealPath("/uploadFile/");
        File folader=new File(realPath);
        if(!folader.isDirectory())
        {
            folader.mkdirs();
        }
        String oldName=uploadFile.getOriginalFilename();
        String newName= UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try {
            uploadFile.transferTo(new File(folader,newName));
            String filePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/uploadFile/"+newName;
            log.info("旧文件名："+oldName+"上传路径："+filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @RequestMapping(value="/uploads", method= RequestMethod.POST )
    public String fileUploads(MultipartFile [] uploadFiles, HttpServletRequest request,String type)  {
        Map<String,String[]> map=new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File folader = new File(realPath);
        if (!folader.isDirectory()) {
            folader.mkdirs();
        }
        log.info("通过["+type+"]上传附件个数："+uploadFiles.length);
        for(MultipartFile uploadFile:uploadFiles) {
            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
                uploadFile.transferTo(new File(folader, newName));
                String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + newName;
                map.put("通过["+type+"]上传"+oldName,new String[]{filePath,realPath+newName});
            } catch (IOException e) {
                log.info(e.getMessage());            }
        }

        request.setAttribute("map",map);
        return "filepage";
    }


}
