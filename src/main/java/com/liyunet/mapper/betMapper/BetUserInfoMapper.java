package com.liyunet.mapper.betMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.bet.BetUserInfo;
import com.liyunet.domain.bet.BetUserInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@MybatisMapper
public interface BetUserInfoMapper {
	int countByExample(BetUserInfoExample example);

	int deleteByExample(BetUserInfoExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(BetUserInfo record);

	int insertSelective(BetUserInfo record);

	List<BetUserInfo> selectByExample(BetUserInfoExample example);

	BetUserInfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") BetUserInfo record, @Param("example") BetUserInfoExample example);

	int updateByExample(@Param("record") BetUserInfo record, @Param("example") BetUserInfoExample example);

	int updateByPrimaryKeySelective(BetUserInfo record);

	int updateByPrimaryKey(BetUserInfo record);

	// 查找每个选项的数量
	Double selectsingleNum(@Param("id") Integer id);

	// 查找每个选项人数
	Double selectsingleCount(@Param("id") Integer id);

	// 查总数量
	String selectNum(@Param("id") Integer id);

	// 查总人数
	String selectCount(@Param("id") Integer id);

	// 查底部信息
	List<Map<String, Object>> selectNots(@Param("id") Integer id);

	// 查询选项的投资
	String selectsumByop(@Param("id") Integer id, @Param("userId") Integer userId);

	// 查竞猜所有下的人
	List<BetUserInfo> selectPeopleBid(@Param("id") Integer id);

}