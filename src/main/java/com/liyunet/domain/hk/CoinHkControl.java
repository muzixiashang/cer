package com.liyunet.domain.hk;

public class CoinHkControl {
    private Integer id;

    private String timeControl;

    private String dumControl;

    private String elseoneControl;

    private String elsetwoControl;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeControl() {
        return timeControl;
    }

    public void setTimeControl(String timeControl) {
        this.timeControl = timeControl == null ? null : timeControl.trim();
    }

    public String getDumControl() {
        return dumControl;
    }

    public void setDumControl(String dumControl) {
        this.dumControl = dumControl == null ? null : dumControl.trim();
    }

    public String getElseoneControl() {
        return elseoneControl;
    }

    public void setElseoneControl(String elseoneControl) {
        this.elseoneControl = elseoneControl == null ? null : elseoneControl.trim();
    }

    public String getElsetwoControl() {
        return elsetwoControl;
    }

    public void setElsetwoControl(String elsetwoControl) {
        this.elsetwoControl = elsetwoControl == null ? null : elsetwoControl.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}