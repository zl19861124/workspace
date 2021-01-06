package com.micro.webpage.controller;

import com.micro.webpage.model.TestTab;
import com.micro.webpage.service.TestTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestTabController {
    @Autowired
    TestTabService testTabService;
    @RequestMapping("/getTestInfo")
    @ResponseBody
    public    List<TestTab>  getTestInfo(HttpServletRequest request, ModelMap model){
        List<TestTab> user = this.testTabService.getAll();
        System.out.println(user);
        return user;
    }
}
