package com.liyunet.vo.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyunan on 2018/9/4.
 */
public class PropertyVo {

    private List<MoneyDetails> list = new ArrayList<>();

    private String total;

    public List<MoneyDetails> getList() {
        return list;
    }

    public void setList(List<MoneyDetails> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
