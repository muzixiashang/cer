package com.liyunet.interceptor;

import com.liyunet.common.ResponseCode;
import com.liyunet.common.json.BaseJSONModel;
import com.liyunet.common.json.DataJSONModel;
import com.liyunet.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by wuyunan on 2018/5/6.
 */
    @Aspect
    @Component
    @Order(Ordered.HIGHEST_PRECEDENCE)
public class APIResponseInterceptor {

    private static final String zh = "系统繁忙,请稍后重试!";
    private static final String en = "The system is busy. Please try again later!";

    private static Logger opLogger = LogManager.getLogger("exception");

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && " +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void response() {

    }


    @Around("response()")
    public Object around(ProceedingJoinPoint point) {

        Object[] args = point.getArgs();
        HttpServletRequest request = null;

        for (Object object: args) {
            if (object instanceof HttpServletRequest) {
                request = (HttpServletRequest)object;
                break;
            }
        }

        if (request == null) {
            throw new RuntimeException("HttpServletServletRequest参数没有填写");
        }

        DataJSONModel json = new DataJSONModel();
        BaseJSONModel.JSONModelState state = json.createState();

        // 获取语言
        String languageType = request.getParameter("languageType");

        try {
            Object o = point.proceed();

            if (!(o instanceof DataJSONModel)) {
                json.setData(o);
                state.ajaxSuccess("success");
                o = json;
            }

            return o;
        } catch (ServiceException e) {
//            opLogger.error("业务异常!", e);
            state.setCode(e.getCode());
            if (Objects.equals(languageType, "en")) {
                state.setMsg(e.getEn());
            } else {
                state.setMsg(e.getMessage());
            }

        } catch (Exception e) {
            handleUnexpectedException(state, languageType, e);
        } catch (Throwable throwable) {
            handleUnexpectedException(state, languageType, new Exception(throwable));
        }

        return json;
    }


    private void handleUnexpectedException(BaseJSONModel.JSONModelState state, String languageType, Exception e) {
        opLogger.error("未知异常!", e);
        state.setCode(ResponseCode.AJAXSYSTEMERROR.getValue());
        if (Objects.equals(languageType, "en")) {
            state.setMsg(en);
        } else {
            state.setMsg(zh);
        }
    }
}
