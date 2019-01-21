package com.liyunet.mapper.userMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@MybatisMapper
public interface PushUserMapper {

    //判断用户名是否存在
    int getUserInfoByName(@Param("account") String account);
    //保存用户创建用户
    void savePushUserInfo(BlockUserInfo enpUser);
    //获得用户,根据名字
    List<BlockUserInfo> getUserInfoByUserName(@Param("userName") String userName);
    //查询用户信息
    int getUserInfoByVo(@Param("account") String account);
    //获取验证码
    List<InvitationCode> getInvitationCode(@Param("account") String account, @Param("code") String code);

    List<UserInfo> getUserInfoByAccount(@Param("username") String username);

    List<BlockUserInfo> getBlockUserInfo(@Param("uId") String uId);

    List<AssociatedAccount> getAntiAddiction(@Param("uId") String uId, @Param("project") String project);

    List<AssociatedAccount> getAssociatedAccount(@Param("id") Integer id,@Param("type") int type);

    int saveAssociatedAccount(AssociatedAccount associatedAccount);

}