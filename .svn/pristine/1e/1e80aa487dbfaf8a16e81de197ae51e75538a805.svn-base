package com.liyunet.mapper.community;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.BlockUserInfo;
import com.liyunet.domain.FBTPrice;
import com.liyunet.domain.InvitationCode;
import com.liyunet.domain.LoginLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuyunan on 2018/4/19.
 */
@Repository
@MybatisMapper
public interface PushCommonMapper {
    //查询是否存在此验证码
    int  getByCode(@Param("code") String code);

    int saveInvitation_code(InvitationCode ic);
    //查询验证码
    List<InvitationCode> getByCodeByaccount(@Param("code") String code, @Param("account") String account);
    //保存登录
    void LoginLog(LoginLog log);
    //修改验证码状态
    int updateInvitationCode(InvitationCode ic);
    //保存修改的密码
    int updateUserInfoForPwd(BlockUserInfo enpUser);

    List<FBTPrice> getFBTPrice();

    void updateFBTPrice(FBTPrice fbtPrice);

    void saveFBTPrice(FBTPrice fbtPrice);
}
