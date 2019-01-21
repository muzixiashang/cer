package com.liyunet.mapper.automation;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@MybatisMapper
@Repository
public interface AutomationMapper {


    List<AssociatedAccount> getAssociatedAccount();

    List<AntiAddiction> getAntiAddiction(@Param("userId") String userId);

    void updateAntiAddiction(AntiAddiction antiAddiction);

    void saveUpdateAntiAddiction(UpdateAntiAddiction updateAntiAddiction);

    List<UserInfo> getUserInfo(@Param("userId") String userId);

    void updateUserInfo(UserInfo userInfo);

    void saveUpdateBlockId(UpdateBlockId updateBlockId);

    int getAssociatedAccountCount(@Param("userId") String userId);

    List<AssociatedAccount> getAssociatedAccountByUserAccount(@Param("username") String username, @Param("id") String id);

    void saveAssociatedAccount(AssociatedAccount associatedAccount1);

    void saveUpdateAssociatedAccount(UpdateAssociatedAccount updateAssociatedAccount);

    int getAntiAddictionByidentityCard(@Param("identityCard") String identityCard);
}
