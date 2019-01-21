package com.liyunet.mapper.betMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.bet.BetBannerInfo;
import com.liyunet.domain.bet.BetBannerInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface BetBannerInfoMapper {
    int countByExample(BetBannerInfoExample example);

    int deleteByExample(BetBannerInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BetBannerInfo record);

    int insertSelective(BetBannerInfo record);

    List<BetBannerInfo> selectByExample(BetBannerInfoExample example);

    BetBannerInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BetBannerInfo record, @Param("example") BetBannerInfoExample example);

    int updateByExample(@Param("record") BetBannerInfo record, @Param("example") BetBannerInfoExample example);

    int updateByPrimaryKeySelective(BetBannerInfo record);

    int updateByPrimaryKey(BetBannerInfo record);
}