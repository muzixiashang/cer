package com.liyunet.domain.bet;

public class AppControl {
    private Integer id;

    private Integer eggStatus;

    private Integer serverStatus;

    private String serverContext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEggStatus() {
        return eggStatus;
    }

    public void setEggStatus(Integer eggStatus) {
        this.eggStatus = eggStatus;
    }

    public Integer getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(Integer serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getServerContext() {
        return serverContext;
    }

    public void setServerContext(String serverContext) {
        this.serverContext = serverContext == null ? null : serverContext.trim();
    }
}