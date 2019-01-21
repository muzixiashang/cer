package com.liyunet.mapper.userMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.controller.AndroidController;
import com.liyunet.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuyunan on 2018/5/11.
 */
@MybatisMapper
@Repository
public interface CertificationMapper {

    //根据身份证号查询
    List<AntiAddiction> getAntiAddiction(@Param("identityCard") String identityCard);

    //添加认证的项目
    int updateAntiAddiction(AntiAddiction antiAddiction);
    //修改身份证认证
    void updateAntiAddictionAll(AntiAddiction antiAddiction);
    //插入身份证认证
    void saveAntiAddiction(AntiAddiction antiAddiction);
    //生成帐号
    void saveUserAccount(UserInfo userInfo);
    //获取用户
    List<UserInfo> getUserInfo(@Param("identityCard") String identityCard);
    //获取用户
    List<UserInfo> getUserInfoByidentityCard(@Param("identityCard") String identityCard, @Param("blockAddress") String blockAddress);
    //获取该账户的绑定帐号
    List<AssociatedAccount> getAssociatedAccount(@Param("uId") String uId);
    //保存绑定帐号
    void saveAssociatedAccount(AssociatedAccount associatedAccount);
    //根据id查询用户
    List<BlockUserInfo> getBlockUserInfo(@Param("userId") Integer userId);
    //获取登录状态
    List<LoginLog> getLoginLog(@Param("userId") Integer userId);
    //根据id获取区块靓号
    List<UserInfo> getUserInfoById(@Param("uId") String uId);
    //根据id查询身份认证
    List<AntiAddiction> getAntiAddictionById(@Param("uId") Integer uId);
    //根据token判断是否绑定

    List<UserInfo> getUserInfoByToken(@Param("token") String token,@Param("project")String project);

    List<UserInfoLoginLog> getUserInfoLoginLog(@Param("id") Integer id);
    //身份认证
    List<AntiAddiction> getAntiAddictionByToken(@Param("token") String token,@Param("project") String project);

    void saveLogin(UserInfoLoginLog loginLog);
    //查询是否存在此手机号
    List<UserInfo> getUserInfoByPhone(@Param("userAccount") String userAccount);

    List<UserInfo> getUserInfoByIdentiy(@Param("identityCard") String identityCard);

    List<UserInfo> getUserInfoByEmail(@Param("userAccount") String userAccount);

    List<UserInfo> getUserInfoByid(@Param("userId") String userId);

    List<AssociatedAccount> getAntiAddictionByUseridOrProject(@Param("userId") String userId, @Param("project") String project);

    List<AssociatedAccount> getAssociatedAccountByToken(@Param("userId") String userId,@Param("project") String project);

    List<AntiAddiction> getAntiAddictionByUserUId(@Param("uId") String uId);

    List<AssociatedAccount> getAssociatedAccountByUserIdOrproject(@Param("userId") String userId, @Param("project") String project);

    List<AssociatedAccount> getUserInfoByBlockAddress(@Param("blockAddress") String blockAddress,@Param("project") String project);

    List<UserInfo> getAssociatedAccountByTokenOrAccount(@Param("userId") String userId, @Param("project") String project,@Param("account") String account);

    List<AntiAddiction> getEggKycSuccess(@Param("project")String project);

    List<UserInfo> getUserInfoByUserAccount(@Param("userAccount") int userAccount);
}
