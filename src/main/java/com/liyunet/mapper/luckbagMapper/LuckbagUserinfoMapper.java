package com.liyunet.mapper.luckbagMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.luckbag.LuckbagUserinfo;
import com.liyunet.domain.luckbag.LuckbagUserinfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface LuckbagUserinfoMapper {
    int countByExample(LuckbagUserinfoExample example);

    int deleteByExample(LuckbagUserinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LuckbagUserinfo record);

    int insertSelective(LuckbagUserinfo record);

    List<LuckbagUserinfo> selectByExample(LuckbagUserinfoExample example);

    LuckbagUserinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LuckbagUserinfo record, @Param("example") LuckbagUserinfoExample example);

    int updateByExample(@Param("record") LuckbagUserinfo record, @Param("example") LuckbagUserinfoExample example);

    int updateByPrimaryKeySelective(LuckbagUserinfo record);

    int updateByPrimaryKey(LuckbagUserinfo record);

    //获取收红包记录
	List<LuckbagUserinfo> getaccepLuckBagHistory(@Param("userId")Integer userId, @Param("page")Integer page,@Param("rows") Integer rows);
  
	//获取收到红包的数量和金额
	Map<Object, Object> selectCountByuserId(@Param("userId")Integer userId);
}