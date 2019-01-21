package com.liyunet.interceptor;

import com.liyunet.common.pushToken.PushAuthHelper;
import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuyunan on 2018/5/6.
 */
@Aspect
@Component
@Order(100)
public class APIAuhtorizationIntercetors {
    private static Logger opLogger = LogManager.getLogger("exception");

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && " +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping) &&" +
            "@annotation(com.liyunet.interceptor.anno.HTTPPublicToken)")
    private void response() {

    }


    @Around("response()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

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

        String token = request.getParameter("token");
        if (token == null || "".equals(token)) {
            args[0] = null;
            return point.proceed(args);
        }
        try {
            PushTokenInfo ti = null;
            ti = PushAuthHelper.verifyToken(token);
            for (int i = 0; i < args.length; i++) {
                Object o = args[i];
                if (o instanceof PushTokenInfo) {
                    args[i] = ti;
                }
            }
        } catch (Exception e){
            throw ServiceException.userException("Authorization fail", "请重新登录");
        }

//        try {
            return point.proceed(args);
//        } catch (Throwable throwable) {
//            throw new RuntimeException(throwable);
//        }
    }
}
