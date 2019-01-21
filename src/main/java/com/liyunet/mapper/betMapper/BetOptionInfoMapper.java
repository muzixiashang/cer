package com.liyunet.mapper.betMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.bet.BetOptionInfo;
import com.liyunet.domain.bet.BetOptionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface BetOptionInfoMapper {
    int countByExample(BetOptionInfoExample example);

    int deleteByExample(BetOptionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BetOptionInfo record);

    int insertSelective(BetOptionInfo record);

    List<BetOptionInfo> selectByExample(BetOptionInfoExample example);

    BetOptionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BetOptionInfo record, @Param("example") BetOptionInfoExample example);

    int updateByExample(@Param("record") BetOptionInfo record, @Param("example") BetOptionInfoExample example);

    int updateByPrimaryKeySelective(BetOptionInfo record);

    int updateByPrimaryKey(BetOptionInfo record);
}