package com.liyunet.mapper.betMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.bet.BetInfo;
import com.liyunet.domain.bet.BetInfoExample;
import com.liyunet.domain.bet.BetUserInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@MybatisMapper
public interface BetInfoMapper {
	int countByExample(BetInfoExample example);

	int deleteByExample(BetInfoExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(BetInfo record);

	int insertSelective(BetInfo record);

	List<BetInfo> selectByExample(BetInfoExample example);

	BetInfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") BetInfo record, @Param("example") BetInfoExample example);

	int updateByExample(@Param("record") BetInfo record, @Param("example") BetInfoExample example);

	int updateByPrimaryKeySelective(BetInfo record);

	int updateByPrimaryKey(BetInfo record);

	// 查竞猜list
	List<BetInfo> selectBetListtwo(@Param("page") Integer page, @Param("rows") Integer rows);

	// 查竞猜list
	List<BetInfo> selectBetListone(@Param("page") Integer page, @Param("rows") Integer rows);

	// 查竞猜记录
	List<BetInfo> selectBetHistoryList(@Param("userId") Integer userId, @Param("status") Integer status);

	

}