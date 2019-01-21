package com.liyunet.domain.hk;

public class CoinHkContract {
    private Integer id;

    private String contract;

    private Integer pid;

    private String substate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getSubstate() {
        return substate;
    }

    public void setSubstate(String substate) {
        this.substate = substate == null ? null : substate.trim();
    }
}