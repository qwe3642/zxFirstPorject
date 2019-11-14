/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  ${className}Mapper.java
 * 描       述:  <描述>
 * 修改人:  ${username}
 * 修改时间:  ${currentDate}
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.timesoft.${module}.mapper;

import org.springframework.stereotype.Component;
import com.timesoft.common.mapper.BaseMapper;
import com.timesoft.${module}.dto.${className}Dto;
/**
 * <p>项目名称：${projectName}<p> 
 * <p>类名称：${className}Mapper<p> 
 * <p>类描述：${codeName}Mapper<p>
 * @author 创建人：${username} 
 * @author 创建时间：${currentDate}
 * @author 修改人：${username}
 * @author 修改时间：${currentDate}
 * @author 修改备注：
 * 
 * @version
 * 
 */
@Component("com.timesoft.${module}.mapper.${className}Mapper")
public interface ${className}Mapper extends BaseMapper<${className}Dto> {
	
}
