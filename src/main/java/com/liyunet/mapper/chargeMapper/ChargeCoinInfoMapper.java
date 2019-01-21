package com.liyunet.mapper.chargeMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.charge.ChargeCoinInfo;
import com.liyunet.domain.charge.ChargeCoinInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface ChargeCoinInfoMapper {
    int countByExample(ChargeCoinInfoExample example);

    int deleteByExample(ChargeCoinInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeCoinInfo record);

    int insertSelective(ChargeCoinInfo record);

    List<ChargeCoinInfo> selectByExample(ChargeCoinInfoExample example);

    ChargeCoinInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChargeCoinInfo record, @Param("example") ChargeCoinInfoExample example);

    int updateByExample(@Param("record") ChargeCoinInfo record, @Param("example") ChargeCoinInfoExample example);

    int updateByPrimaryKeySelective(ChargeCoinInfo record);

    int updateByPrimaryKey(ChargeCoinInfo record);
}