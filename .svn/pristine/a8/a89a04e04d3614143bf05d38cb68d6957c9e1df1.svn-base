package com.liyunet.mapper.game_api;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository
@MybatisMapper
public interface GameApiMapper {

    //获取身份证
    List<AntiAddiction> getAntiAddictionLevel(@Param("identityCard") String identityCard);

    List<UserInfo> getUserInfo(@Param("userId") String userId);


    List<GameMerchantMessage> getGameMerchantMessage(@Param("mchId") String mch_id,@Param("appId") String app_id);

    List<UserInfo> getUserInfoByUserAccount(@Param("userAccount") String userAccount);

    int saveGameMessage(GameMessage gameMessage);

}
