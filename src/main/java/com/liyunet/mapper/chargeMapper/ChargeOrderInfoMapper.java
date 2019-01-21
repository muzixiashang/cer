package com.liyunet.mapper.chargeMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.charge.ChargeOrderInfo;
import com.liyunet.domain.charge.ChargeOrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@MybatisMapper
public interface ChargeOrderInfoMapper {
	int countByExample(ChargeOrderInfoExample example);

	int deleteByExample(ChargeOrderInfoExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ChargeOrderInfo record);

	int insertSelectKey(ChargeOrderInfo record);

	int insertSelective(ChargeOrderInfo record);

	List<ChargeOrderInfo> selectByExample(ChargeOrderInfoExample example);

	ChargeOrderInfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ChargeOrderInfo record,
			@Param("example") ChargeOrderInfoExample example);

	int updateByExample(@Param("record") ChargeOrderInfo record, @Param("example") ChargeOrderInfoExample example);

	int updateByPrimaryKeySelective(ChargeOrderInfo record);

	int updateByPrimaryKey(ChargeOrderInfo record);
}