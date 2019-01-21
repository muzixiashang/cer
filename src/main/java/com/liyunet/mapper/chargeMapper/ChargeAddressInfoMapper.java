package com.liyunet.mapper.chargeMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.charge.ChargeAddressInfo;
import com.liyunet.domain.charge.ChargeAddressInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface ChargeAddressInfoMapper {
    int countByExample(ChargeAddressInfoExample example);

    int deleteByExample(ChargeAddressInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeAddressInfo record);

    int insertSelective(ChargeAddressInfo record);

    List<ChargeAddressInfo> selectByExample(ChargeAddressInfoExample example);

    ChargeAddressInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChargeAddressInfo record, @Param("example") ChargeAddressInfoExample example);

    int updateByExample(@Param("record") ChargeAddressInfo record, @Param("example") ChargeAddressInfoExample example);

    int updateByPrimaryKeySelective(ChargeAddressInfo record);

    int updateByPrimaryKey(ChargeAddressInfo record);
}