package com.liyunet.common.pushToken;


import com.liyunet.common.constant.Constant;
import com.liyunet.domain.BlockUserInfo;
import com.liyunet.vo.user.PushUserInfoVo;

import java.util.concurrent.TimeUnit;

public class PushCommonVoUtil {

	public static PushUserInfoVo getUserInfoVo(BlockUserInfo dbUser, String languageType, String type){
		PushUserInfoVo vo=new PushUserInfoVo();
		vo.setId(dbUser.getId());
		vo.setNickName(dbUser.getNick_name());
		vo.setUsername(dbUser.getUsername());
		vo.setUserDesc(dbUser.getUserDesc()==null?"":dbUser.getUserDesc());
		vo.setUserSex(dbUser.getUserSex());
		vo.setUserHeadPicPath(dbUser.getHeadPicPath()==null?"":dbUser.getHeadPicPath());
		String uesrSexStr=dbUser.getUserSex()==0?"男":"女";
		if("en".equals(languageType)){
			uesrSexStr=dbUser.getUserSex()==0?"male":"female";
		}else if("zh".equals(languageType)){
			uesrSexStr=dbUser.getUserSex()==0?"男":"女";
		}
		vo.setUserSexStr(uesrSexStr);
		String jwt = PushAuthHelper.createJsonWebToken(dbUser.getId(),type, TimeUnit.HOURS, Constant.COOKIE_EXPIRE_HOURS);
		vo.setToken(jwt);
		vo.setPhoneNum(dbUser.getPhoneNum()==null?"":dbUser.getPhoneNum());
		return vo;
	}
}
