package com.developproject.test.mapper;

import com.developproject.test.dto.JcxxNsztDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface JcxxNsztMapper {

    @Select("select * from ut_jcxx_nszt where id = #{id}")
    public JcxxNsztDto selectJcxx(String id);
}
