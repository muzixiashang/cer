package com.liyunet.exception;

import com.liyunet.common.ResponseCode;

/**
 * Created by wuyunan on 2018/5/6.
 */
public class ServiceException extends RuntimeException {

    private String code;

    private String en;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ServiceException(String code, String en) {
        this.code = code;
        this.en = en;
    }

    public ServiceException(String message, String code, String en) {
        super(message);
        this.code = code;
        this.en = en;
    }

    public ServiceException(String message, Throwable cause, String code, String en) {
        super(message, cause);
        this.code = code;
        this.en = en;
    }

    public ServiceException(Throwable cause, String code, String en) {
        super(cause);
        this.code = code;
        this.en = en;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String en) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.en = en;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public static ServiceException userException(String msg) {
        return new ServiceException(msg, ResponseCode.AJAXSERVICEERROR.getValue());
    }

    public static ServiceException userException(String enMsg, String zhMsg) {
        return new ServiceException(zhMsg, ResponseCode.AJAXSERVICEERROR.getValue(), enMsg);
    }
}
