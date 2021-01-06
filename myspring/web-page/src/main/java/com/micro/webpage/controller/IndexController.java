package com.micro.webpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping(value = "index")
    private String index(ModelMap map)
    {
        // addAttribute是不允许添加空值的key，put是允许的
        map.put("title","欢迎来到我的页面");
        map.put("urlbaidu","www.baidu.com");
        Map<String,String> navMap=new HashMap<String, String>();
        navMap.put("首页","fileupload");
        navMap.put("#主页","index");
        navMap.put("联系我们","ftlIndex");
        map.put("navMap",navMap);
        map.addAttribute("content","一个小小的thymeleaf测试而已");
        List<String> lst=new ArrayList<String>();
        lst.add("时间");
        lst.add("地点");
        lst.add("人物");
        map.addAttribute("lst",lst);

        return  "index";
    }
    @RequestMapping(value = "fileupload")
    private String fileupload()
    {
        return  "fileupload";
    }
    @RequestMapping(value = "ftlIndex")
    private String ftlIndex()
    {
        System.out.println("freemrark");
        return  "ftlIndex";
    }
}
