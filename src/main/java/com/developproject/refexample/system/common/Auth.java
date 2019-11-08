package com.developproject.refexample.system.common;

public @interface Auth {
    /**
     * 是否验证登陆 true=验证 ,false = 不验证
     * @return
     */
    public boolean verifyLogin() default true;


    /**
     * 是否验证URL true=验证 ,false = 不验证
     * @return
     */
    public boolean verifyURL() default true;

    /**
     * 标题
     * @return
     */
    public String title() default "";

    /**
     * 标题 Json=json数据,View=页面,String=字符串,Stream=文件流
     * @return
     */
    public String type() default "Json";
}
