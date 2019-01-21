package com.liyunet.mapper.userMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuyunan on 2018/5/11.
 */
@MybatisMapper
@Repository
public interface ApiRegAddressMapper {


    //判断用户名是否存在
    int getUserInfoByName(@Param("account") String account);

    int getByCode(@Param("code") String code);

    int saveInvitation_code(InvitationCode ic);

    List<UserInfo> getUserInfoByToken(@Param("token") String token,@Param("project")String project);
    //查询验证码是否正确
    List<InvitationCode> getByCodeByaccount(@Param("code") String code,@Param("email") String email,@Param("type") String type);
    //修改绑定邮箱
    void updateUserInfo(UserInfo userInfo);

    int updateInvitationCode(InvitationCode ic);

    void updateUserInfoPwd(UserInfo userInfo);

    int getUserinfoByPhone(@Param("phone") String phone);

    int getUserinfoByemail(@Param("email") String email);
}
