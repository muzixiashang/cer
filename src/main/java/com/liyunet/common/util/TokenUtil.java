package com.liyunet.common.util;

import com.liyunet.common.AuthHelper;
import com.liyunet.common.TokenInfo;

/**
 */
public class TokenUtil {

    public static String getUserId(String token) {
        TokenInfo ti = AuthHelper.verifyToken(token);
        Integer userId = ti.getUserId();
        return userId + "";
    }
}
