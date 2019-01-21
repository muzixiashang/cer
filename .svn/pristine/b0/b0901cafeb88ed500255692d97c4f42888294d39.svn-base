package com.liyunet.mapper.community;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuyunan on 2018/4/19.
 */
@Repository
@MybatisMapper
public interface BandingMapper {

    List<AssociatedAccount> getAssociatedAccount(@Param("project") String project,@Param("account") String account);

    List<AssociatedAccount> getAssociatedAccountByblockId(@Param("blockId") String blockId,@Param("project")String project);

    List<UserInfo> getUserInfo(@Param("blockUserId") String blockUserId);

    void saveAssociatedAccount(AssociatedAccount associatedAccount);

    List<UserInfo> getUserInfoByToken(@Param("userId") String userId,@Param("project") String project);

    List<AssociatedAccount> getAssociatedAccountByToken(@Param("userId") String userId,@Param("project") String project);

    int getUserInfoByPhone(@Param("phone") String phone);

    int saveInvitationCode(InvitationUserCode ic);

    int getUserInfoByTokenCount(@Param("userId") String userId, @Param("project") String project);

    List<InvitationUserCode> getByCodeByaccount(@Param("code") String code,@Param("phone") String phone,@Param("project") String project,@Param("userId") String userId,@Param("type")String type);

    void updateInvitationCode(InvitationUserCode ic);

    int getUserInfoByEmail(@Param("userAccount") String userAccount);

    List<UserInfo> getUserInfoByIdentiy(@Param("identityCard") String identityCard);

    List<AntiAddiction> getAntiAddiction(@Param("identityCard") String identityCard);

    int saveAntiAddiction(AntiAddiction antiAddiction);

    void saveUserAccount(UserInfo userInfo);

    void updateUserInfoPwd(UserInfo userInfo);

    List<UserInfo> judgeBlockId(@Param("blockId") String blockId);

    List<UserInfo> getUserInfoByPhoneOrIdentiyCard(@Param("phone") String phone, @Param("identityCard") String identityCard);

    List<UserInfo> getUserInfoById(@Param("userId") String userId,@Param("blockId")String blockId);

    List<AntiAddiction> getAntiAddictionByName(@Param("identityCard") String identityCard,@Param("userName") String userName,@Param("phone")String phone);

    List<UserInfo> getUserInfoByUserId(@Param("userId") String userId);

    void updateAntiAddiction(AntiAddiction antiAddiction);

    List<AssociatedAccount> getAssociatedAccountByUserId(@Param("uId") String uId);

    List<AssociatedAccount> getAssociatedAccountByuIdOrProject(@Param("userId") String userId, @Param("project") String project);

    void updataBlockAddress(UserInfo userInfo);

    List<UserInfo> getUserInfoByuId(@Param("userId") String userId);
}
