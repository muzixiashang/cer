package com.liyunet.util;

/**
 * Created by wuyunan on 2018/4/2.
 */
public class UuidUtil {
    //获取32位UUID
    public static String getUUid(){
        String UUID = java.util.UUID.randomUUID().toString();
        UUID = UUID.replace("-","");
        return UUID;
    }

    public static String getUUid16(){
        String UUID = java.util.UUID.randomUUID().toString();
        UUID = UUID.replace("-","");
        String substring = UUID.substring(0, 16);
        return substring;
    }
}
