package com.developproject.refexample.system.utils;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>更新者：</b>zhangzhw<br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>		
 */

public class CreateJavaSource {

    public static void main(String[] args) throws IOException, SQLException {
	Map<String, String> map = new HashMap<String, String>();
	map.put("url", "jdbc:mysql://127.0.0.1:3306/nike_db?useUnicode=true&useSSL=true&verifyServerCertificate=false&characterEncoding=utf-8&serverTimezone=GMT");// 数据库链接地址
	map.put("dbusername", "root");// 数据库用户名
	map.put("dbpassWord", "root");// 数据库密码
	map.put("projectName", "developproject.refexample");// 工程名：htta-system
	map.put("module", "quart");// 模块包名：system
	map.put("username", "zhangx");// 创建者
	map.put("tableName", "ut_job_configure");// 表名
	map.put("codeName", "定时任务配置信息表");// 表中文名

	CreateJava createJava = new CreateJava();
	try {
	    createJava.createSource(map);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
