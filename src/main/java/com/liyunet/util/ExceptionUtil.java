package com.liyunet.util;


import com.liyunet.common.json.BaseJSONModel;

/**
 * 异常添加
 */
public class ExceptionUtil {
    private static final String zh = "系统繁忙,请稍后重试!";
    private static final String en = "The system is busy. Please try again later!";
    //系统错误20000
    public static BaseJSONModel.JSONModelState getException(String en, String zh, BaseJSONModel.JSONModelState state, String languageType)
    {
        if("en".equals(languageType)){
            state.ajaxError(en);
        }else if("zh".equals(languageType)){
            state.ajaxError(zh);
        }
        return state;
    }
    public static BaseJSONModel.JSONModelState getException(BaseJSONModel.JSONModelState state, String languageType)
    {
        if("en".equals(languageType)){
            state.ajaxError(en);
        }else if("zh".equals(languageType)){
            state.ajaxError(zh);
        }
        return state;
    }
    //用户错误的跳转20004
    public static BaseJSONModel.JSONModelState getServiceException(String en, String zh, BaseJSONModel.JSONModelState state, String languageType)
    {
        if("en".equals(languageType)){
            state.ajaxServiceError(en);
        }else if("zh".equals(languageType)){
            state.ajaxServiceError(zh);
        }
        return state;
    }
    public static BaseJSONModel.JSONModelState getServiceException(BaseJSONModel.JSONModelState state, String languageType)
    {
        if("en".equals(languageType)){
            state.ajaxServiceError(en);
        }else if("zh".equals(languageType)){
            state.ajaxServiceError(zh);
        }
        return state;
    }

    //用户错误的20004
    public static BaseJSONModel.JSONModelState getSkipException(String en, String zh, BaseJSONModel.JSONModelState state, String languageType)
    {
        if("en".equals(languageType)){
            state.ajaxSkipError(en);
        }else if("zh".equals(languageType)){
            state.ajaxSkipError(zh);
        }
        return state;
    }
    public static BaseJSONModel.JSONModelState getSkipException(BaseJSONModel.JSONModelState state, String languageType)
    {
        if("en".equals(languageType)){
            state.ajaxSkipError(en);
        }else if("zh".equals(languageType)){
            state.ajaxSkipError(zh);
        }
        return state;
    }
}
