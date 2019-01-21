package com.liyunet.domain.gameapi;

public class GameApiExchangeRate {
    private Integer id;

    private String bidtExchangeRate;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBidtExchangeRate() {
        return bidtExchangeRate;
    }

    public void setBidtExchangeRate(String bidtExchangeRate) {
        this.bidtExchangeRate = bidtExchangeRate == null ? null : bidtExchangeRate.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}