package com.liyunet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.http.PostAndGet;
import com.liyunet.domain.FBTPrice;
import com.liyunet.mapper.community.PushCommonMapper;
import com.liyunet.service.EndTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 */
@Service("endTimeService")
@Transactional
public class EndTimeServiceImpl implements EndTimeService {


    private final PushCommonMapper pushCommonMapper;

    @Autowired
    public EndTimeServiceImpl(PushCommonMapper pushCommonMapper) {
        this.pushCommonMapper = pushCommonMapper;
    }

    //当前时间
    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        return format1;
    }


    @Override
    public void syncEndTme() {

        String pNes = getPNes("107");
        List<FBTPrice> fbtPrices = pushCommonMapper.getFBTPrice();
        if (fbtPrices != null && fbtPrices.size() > 0) {
            FBTPrice fbtPrice = fbtPrices.get(0);
            fbtPrice.setCreateTime(getTime());
            fbtPrice.setPrice(pNes);
            pushCommonMapper.updateFBTPrice(fbtPrice);
        }else {
            FBTPrice fbtPrice = new FBTPrice();
            fbtPrice.setPrice(pNes);
            fbtPrice.setCreateTime(getTime());
            pushCommonMapper.saveFBTPrice(fbtPrice);
        }
    }
    public String getPNes(String num) {
        String sendGet = PostAndGet.sendGet("https://old.fubt.top/real/market.html",
                "symbol=" + num);
        JSONObject eggloade1 = JSON.parseObject(sendGet);
        JSONObject data = eggloade1.getJSONObject("data");
        String p_new = data.getString("p_new");
        if (p_new.indexOf(".") > 0) {
            p_new = p_new.replaceAll("0+?$", "");//去掉多余的0
            p_new = p_new.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return p_new;
    }
}
