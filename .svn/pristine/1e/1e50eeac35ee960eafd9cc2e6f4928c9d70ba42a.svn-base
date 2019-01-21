package com.liyunet.mapper.hkMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.hk.CoinHkControl;
import com.liyunet.domain.hk.CoinHkControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface CoinHkControlMapper {
    int countByExample(CoinHkControlExample example);

    int deleteByExample(CoinHkControlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoinHkControl record);

    int insertSelective(CoinHkControl record);

    List<CoinHkControl> selectByExample(CoinHkControlExample example);

    CoinHkControl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoinHkControl record, @Param("example") CoinHkControlExample example);

    int updateByExample(@Param("record") CoinHkControl record, @Param("example") CoinHkControlExample example);

    int updateByPrimaryKeySelective(CoinHkControl record);

    int updateByPrimaryKey(CoinHkControl record);
}