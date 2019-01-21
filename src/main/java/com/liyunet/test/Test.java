package com.liyunet.test;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.blockAddress.CreateBitconAddressUtil;
import com.liyunet.common.constant.Constant;
import com.liyunet.common.http.PostAndGet;
import com.liyunet.common.password.AES;
import com.liyunet.common.pushPassword.PushPasswordHash;
import com.liyunet.common.pushToken.PushAuthHelper;
import com.liyunet.common.util.TokenUtil;
import com.liyunet.util.PushRefinedCalculation;
import org.springframework.beans.factory.annotation.Value;
import sun.misc.BASE64Encoder;

public class Test {
    public static void main(String[] args) {
//
//        try {
//           String  encode = URLEncoder.encode(AES.AESEncode("dj2Cr$kd&E", "mch_id测试" + "1540978388847"), "UTF-8");
//
//            System.out.println(encode);
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        int i  = 0 ;
//        while (i < 1){
//            System.out.println(1);
//            i++;
//        }
//
//        String str = "mch_id测试" + "1540978388847";
//        String s1 = md5Java(str);
//        System.out.println(s1);
//        String s = MD5(str);
//        System.out.println(s.equals(s1));
        String a = "http://123.207.158.196:8099/pictures//identitycard/img/c5a55750-3b8c-48ea-b421-fa6ccaa14103.jpeg";
        int i = a.indexOf("http://123.207.158.196:8099");
        System.out.println(i);
        if(i > -1){
            a = a.replaceAll("http://123.207.158.196:8099","http://api.timetreaty.org");
            System.out.println(a);
        }

    }


    public static String md5Java(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));

            //converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            digest = sb.toString();

        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }

    private static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println(result);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

}
