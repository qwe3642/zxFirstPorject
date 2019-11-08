package com.developproject.refexample.system.action;

import com.developproject.refexample.system.common.ReturnData;
import com.developproject.refexample.system.common.URLUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class BaseAction {

    public ModelAndView forword(String viewName, Map context) {
        return new ModelAndView(viewName, context);
    }

    /**
     * 所有ActionMap 统一从这里获取
     *
     * @return
     */
    public Map<String, Object> getRootMap() {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        // 添加url到 Map中
        rootMap.putAll(URLUtils.getUrlMap());
        return rootMap;
    }

    protected ReturnData setReturnData(String code, String msg, Object returnObject) {
        ReturnData returnData = new ReturnData();
        returnData.setReturnObject(returnObject);
        returnData.setCode(code);
        returnData.setMsg(msg);
        return returnData;

    }
}
