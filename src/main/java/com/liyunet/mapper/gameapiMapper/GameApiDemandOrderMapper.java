package com.liyunet.mapper.gameapiMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.gameapi.GameApiDemandOrder;
import com.liyunet.domain.gameapi.GameApiDemandOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@MybatisMapper
public interface GameApiDemandOrderMapper {
	int countByExample(GameApiDemandOrderExample example);

	int deleteByExample(GameApiDemandOrderExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(GameApiDemandOrder record);

	int insertOverride(GameApiDemandOrder order);

	int insertSelective(GameApiDemandOrder record);

	List<GameApiDemandOrder> selectByExampleWithBLOBs(GameApiDemandOrderExample example);

	List<GameApiDemandOrder> selectByExample(GameApiDemandOrderExample example);

	GameApiDemandOrder selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") GameApiDemandOrder record,
			@Param("example") GameApiDemandOrderExample example);

	int updateByExampleWithBLOBs(@Param("record") GameApiDemandOrder record,
			@Param("example") GameApiDemandOrderExample example);

	int updateByExample(@Param("record") GameApiDemandOrder record,
			@Param("example") GameApiDemandOrderExample example);

	int updateByPrimaryKeySelective(GameApiDemandOrder record);

	int updateByPrimaryKeyWithBLOBs(GameApiDemandOrder record);

	int updateByPrimaryKey(GameApiDemandOrder record);

	// 查订单历史
	List<GameApiDemandOrder> getOrderListBytoken(@Param("userId")Integer userId,@Param("page") Integer page, @Param("rows")Integer rows);

}