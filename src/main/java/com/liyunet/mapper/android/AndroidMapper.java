package com.liyunet.mapper.android;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.*;
import com.liyunet.domain.dto.AppParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository
@MybatisMapper
public interface AndroidMapper {


    List<UserInfo> getuserInfoById(@Param("userId") Integer userId);

    List<AssociatedAccount> getAssociatedAccount(@Param("userId") String userId);

    List<Announcement> getAnnouncement(@Param("page") Integer page,@Param("row") Integer row);

    List<AnnouncementRead> getAnnouncementRead(@Param("uId") String uId,@Param("userId") String userId);

    List<Announcement> getAnnouncementById(@Param("id") String id);

    void saveAnnouncementRead(AnnouncementRead announcementRead);

    List<UserAddress> getUserAddress(@Param("uId") String uId,@Param("page") Integer page,@Param("row") Integer row,@Param("type") String type);

    int saveUserAddress(UserAddress userAddress);

    List<UserAddress> getUserAddressById(@Param("uId") String uId,@Param("id") String id);

    int deleteUserAddress(@Param("id") String id);

    int updateAddress(UserAddress userAddress);

    int getByCode(@Param("code") String code);

    int saveInvitation_code(InvitationCode ic);

    List<InvitationCode> getByCodeByaccount(@Param("code") String code,@Param("email") String email,@Param("type") String type);

    int updateInvitationCode(InvitationCode ic);

    int updateUserInfoFundPwd(UserInfo userInfo);

    List<UserInfo> getUserInfoByToken(@Param("token") Integer userId,@Param("project") String project);

    List<AssociatedAccount> getAssociatedAccountByUserIdAndAppName(@Param("uId") String uId, @Param("project") int project);

    int updateUserInfoPwd(UserInfo userInfo);

    List<UserAddress> selectAddress(@Param("blockId") String blockId,@Param("page") Integer page,@Param("row") Integer row);

    List<UserInfo> getUserInfoByBlockId(@Param("blockId") String blockId);

    List<AntiAddiction> getAntiAddiction(@Param("identityCard") String identityCard);

    List<APPditchId> getAPPditchId(@Param("ditchId") String ditchId,@Param("type")String type);

    List<APPUpdateContent> getAPPUpdateContent(@Param("version") String version);

    List<APPUpdateCondition> getAPPUpdateCondition(@Param("version") String version);

    void saveAPPUserPreciseAddress(APPUserPreciseAddress appUserPreciseAddress);

    List<APPditchId> getAPPditchIdByDitchId(@Param("ditchId") String ditchId);

    List<AppUserMessage> getAppUserMessage(@Param("blockId") String blockId);

    void updateAppUserMessage(AppUserMessage appParameter);

    void insertAppUserMessage(AppUserMessage appParameter);

    List<UserInfo> getUserInfoByblockAddress(@Param("address") String address);

    List<AndroidDownload> getAndroidDownload(@Param("format") String format);

    void updateAndroidDownload(AndroidDownload androidDownload);

    void saveAndroidDownload(AndroidDownload androidDownload);

    List<AndroidLogin> getAndroidLogin(@Param("blockId") String blockId, @Param("createTime") String createTime);

    void saveAndroidLogin(AndroidLogin androidLogin);

    List<AssociatedAccount> getAssociatedAccountnew(@Param("userId") String userId);
}
