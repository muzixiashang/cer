package com.liyunet.common.pushToken;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class PushTokenUtil {
	public static Optional<PushTokenInfo> getJWT(String token){
		PushTokenInfo info;
		try {
			info = PushAuthHelper.verifyToken(token);
		} catch (Exception e) {
			info = null;
		}
		return Optional.ofNullable(info);
	}
	
	public static PushTokenInfo getJWT(HttpServletRequest request){
		//从cookie中得到token
		PushTokenInfo ti = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
        	for (Cookie cookie : cookies) {
        		String cookieName = cookie.getName();
        		if("jwt".equals(cookieName)){
        			String token = cookie.getValue();
        			try {
        				ti = PushAuthHelper.verifyToken(token);
        				return ti;
					} catch (Exception e) {
						return null;
					}
        		}
        	}
        }
        
        
        String header = request.getHeader("Token");
        
        if(StringUtils.isNotEmpty(header)){        	
        	try {
				ti = PushAuthHelper.verifyToken(header);
				return ti;
			} catch (Exception e) {
				return null;
			}
        }
        
		return null;
	}
}
