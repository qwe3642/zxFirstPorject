package com.developproject.test.action;

import com.developproject.test.dto.JcxxNsztDto;
import com.developproject.test.service.JcxxNsztService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/jcxx")
public class JcxxNsztAction {
    @Autowired
    private JcxxNsztService jcxxNsztService;

    public JcxxNsztDto selectNsztById(@PathVariable String id){
        return jcxxNsztService.selectNsztById(id);
    }
}
