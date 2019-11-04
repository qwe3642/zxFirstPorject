package com.developproject.refexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HelloController {
    @Value("${name}")
    private String name;
    @Value("${age}")
    private String age;

    @RequestMapping("/hello")
    public String hello(){
        return name+age;
    }

    @RequestMapping("/getModel")
    public String getModel(Model model){
         model.addAttribute("now",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
         return "hello";
    }
}
