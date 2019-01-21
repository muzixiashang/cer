package com.liyunet.vo.androidVo;

import com.liyunet.domain.APPUpdateContent;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class AppParameterVo {


    private String Time;

    //app版本号
    private String appVersion;

    private List<APPUpdateContent> list = new ArrayList<>();
    //0不需要更新，1可以更新，2需要强制更新
    private Integer status;

    private String url;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public List<APPUpdateContent> getList() {
        return list;
    }

    public void setList(List<APPUpdateContent> list) {
        this.list = list;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
