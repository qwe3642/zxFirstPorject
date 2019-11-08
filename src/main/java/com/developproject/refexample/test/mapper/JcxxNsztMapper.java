package com.developproject.refexample.test.mapper;

import com.developproject.refexample.test.dto.JcxxNsztDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JcxxNsztMapper {

    @Select("select * from ut_jcxx_nszt where id = #{id}")
    public JcxxNsztDto selectJcxx(String id);
}
