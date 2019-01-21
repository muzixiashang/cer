package com.liyunet.mapper.hkMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.hk.CoinHkUserProject;
import com.liyunet.domain.hk.CoinHkUserProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@MybatisMapper
public interface CoinHkUserProjectMapper {
	int countByExample(CoinHkUserProjectExample example);

	int deleteByExample(CoinHkUserProjectExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(CoinHkUserProject record);

	int insertSelectKey(CoinHkUserProject record);

	int insertSelective(CoinHkUserProject record);

	List<CoinHkUserProject> selectByExample(CoinHkUserProjectExample example);

	CoinHkUserProject selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") CoinHkUserProject record,
			@Param("example") CoinHkUserProjectExample example);

	int updateByExample(@Param("record") CoinHkUserProject record, @Param("example") CoinHkUserProjectExample example);

	int updateByPrimaryKeySelective(CoinHkUserProject record);

	int updateByPrimaryKey(CoinHkUserProject record);

	// 新加
	// 查购买数量
	Double selectCoinCount(@Param("id") Integer id);
    //投资记录
	List<CoinHkUserProject> selectHKPayList(@Param("userId")Integer userId,@Param("page") Integer page,@Param("rows") Integer rows);

	List<CoinHkUserProject> getUserPaystate(@Param("userId")Integer userId, @Param("pid")Integer pid);
    //查状态是1和状态是6的
	List<CoinHkUserProject> getProfitBytoken(@Param("userId")Integer userId);
   
}