package com.liyunet.domain;

/**
 * Created by wuyunan on 2018/11/2.
 */
public class AndroidDownload {

    private String id;

    private String createTime;

    private Integer downloadNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }
}
