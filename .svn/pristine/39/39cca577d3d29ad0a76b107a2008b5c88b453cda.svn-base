package com.liyunet.mapper;


import com.liyunet.MybatisMapper;
import com.liyunet.domain.BlockUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuyunan on 2018/5/2.
 */
@Repository
@MybatisMapper
public interface PushDealMapper {
    //根据ID查询用户
    List<BlockUserInfo> getPushUserInfo(@Param("userId") String userId);
}
