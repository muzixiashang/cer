package com.liyunet.mapper.chargeMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.charge.ChargeUserBalance;
import com.liyunet.domain.charge.ChargeUserBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface ChargeUserBalanceMapper {
    int countByExample(ChargeUserBalanceExample example);

    int deleteByExample(ChargeUserBalanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeUserBalance record);

    int insertSelective(ChargeUserBalance record);

    List<ChargeUserBalance> selectByExample(ChargeUserBalanceExample example);

    ChargeUserBalance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChargeUserBalance record, @Param("example") ChargeUserBalanceExample example);

    int updateByExample(@Param("record") ChargeUserBalance record, @Param("example") ChargeUserBalanceExample example);

    int updateByPrimaryKeySelective(ChargeUserBalance record);

    int updateByPrimaryKey(ChargeUserBalance record);
}