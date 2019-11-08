package com.developproject.refexample.test.service;

import com.developproject.refexample.test.dto.JcxxNsztDto;
import com.developproject.refexample.test.mapper.JcxxNsztMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JcxxNsztService {
    @Autowired
    private JcxxNsztMapper mapper;

    public JcxxNsztDto selectNsztById(String id){
        return mapper.selectJcxx(id);
    }
}
