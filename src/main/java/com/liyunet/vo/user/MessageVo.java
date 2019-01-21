package com.liyunet.vo.user;

/**
 * Created by wuyunan on 2018/10/12.
 */
public class MessageVo {

    private String id;

    private String AnnouncementTitle;

    private String AnnouncementCreateTime;

    private Integer isRead;
    //1公告
    private Integer type;

    private Integer readNum;

    public String getAnnouncementTitle() {
        return AnnouncementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        AnnouncementTitle = announcementTitle;
    }

    public String getAnnouncementCreateTime() {
        return AnnouncementCreateTime;
    }

    public void setAnnouncementCreateTime(String announcementCreateTime) {
        AnnouncementCreateTime = announcementCreateTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }
}
