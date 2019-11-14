package com.developproject.refexample.system.utils;

import com.google.common.collect.Maps;
import net.sf.ehcache.Cache;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class Constants {
    public static final String USER_SESSION_KEY = "userKey";
    public static final String AUTH_CODE = "authCode";
    public static final String USER_INFO = "customerInfo";
    public static final String ENP_INFO = "EnpInfo";
    public static final int ADMIN_ID = 1;

    public static Cache cache = SystemCommonF.setNullObj();


    public static WebApplicationContext webApplicationContext;

    public static String super_admin = "admin,root";

    public static String applicationPath = "";

    public static final String TOP_ZZJG_ID = "3a5378ccb4a74c4d8d918036a1455463";
    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("sysconfig.properties");


    public static PropertiesLoader solrProperties = new PropertiesLoader("solr.properties");

    //项目根路径 线上是tomcat的，本地可以配成工作空间项目
    public static String WORK_ROOT_PATH = loader.getProperty("work.root.path");
    //模板根目录
    public static String WORK_TEMPLATE_PATH = loader.getProperty("work.template.path");
    //计划任务启动
    public static String TASK_SCHEDULE = loader.getProperty("task.scheduling");

    //上传根目录地址 http://image.mn606.com/
    public static String UPLOAD_URL = loader.getProperty("upload.url");
    //发票上传目录
    public static String INVOICE_PATH = loader.getProperty("invoice.original");
    public static String INVOICE_BACKUP = loader.getProperty("invoice.backup");
    public static String INVOICE_ERROR = loader.getProperty("invoice.error");
    public static String USERPWD = loader.getProperty("userPwd");
    //上传文件根目录
    public static String UPLOAD_PATH = loader.getProperty("upload.path");

    //搜索的域名
    public static String SEARCH_URL = loader.getProperty("search.url");
    //临时缓冲目录
    public static String TEMP_FOLDER = "/tempfolder/";

    public static final String login_yzm = "yzmimage"; // 登录验证码
    // 当前在线用户
    public static ConcurrentMap<String, Object> onlineUserMap = new ConcurrentHashMap<String, Object>();

    public static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    public static String databaseType = "oracle";


    /**
     * 换行符<br>
     * \n:换行
     */
    public static final String ENTER = "\n";
    /**
     * 显示/隐藏
     */
    public static final String SHOW = "1";
    public static final String HIDE = "0";

    /**
     * 是/否
     */
    public static final String YES = "1";
    public static final String NO = "0";

    /**
     * 对/错
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";


    /**
     * 申报流程
     */
    public static final String SHENBAO = "3e89eae5067d4ea7b9de246d918627d9";


    /**
     * 获取配置
     *
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 页面获取常量
     *
     * @see ${fns:getConst('YES')}
     */
    public static Object getConst(String field) {
        try {
            return Constants.class.getField(field).get(null);
        } catch (Exception e) {
            // 异常代表无配置，这里什么也不做
        }
        return null;
    }

}
