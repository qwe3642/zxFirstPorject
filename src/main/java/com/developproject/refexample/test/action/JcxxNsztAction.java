package com.developproject.refexample.test.action;

import com.developproject.refexample.test.dto.JcxxNsztDto;
import com.developproject.refexample.test.service.JcxxNsztService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JcxxNsztAction {
    @Autowired
    private JcxxNsztService jcxxNsztService;
    @RequestMapping("/getById/{id}")
    public String selectNsztById(@PathVariable String id){
        JcxxNsztDto dto=jcxxNsztService.selectNsztById(id);
        return "hello";
    }
}
