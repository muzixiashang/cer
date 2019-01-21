package com.liyunet.mapper.luckbagMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.luckbag.LuckbagInfo;
import com.liyunet.domain.luckbag.LuckbagInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface LuckbagInfoMapper {
    int countByExample(LuckbagInfoExample example);

    int deleteByExample(LuckbagInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LuckbagInfo record);

    int insertSelective(LuckbagInfo record);

    List<LuckbagInfo> selectByExample(LuckbagInfoExample example);

    LuckbagInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LuckbagInfo record, @Param("example") LuckbagInfoExample example);

    int updateByExample(@Param("record") LuckbagInfo record, @Param("example") LuckbagInfoExample example);

    int updateByPrimaryKeySelective(LuckbagInfo record);

    int updateByPrimaryKey(LuckbagInfo record);
  
    //查找发红包记录
	List<LuckbagInfo> getsendLuckBagHistory(@Param("userId")Integer userId,@Param("page")Integer page,@Param("rows")Integer rows);
   
	//查找发红包金额和数量
	Map<Object,Object> selectCountByuserId(@Param("userId")Integer userId);
    
	//查找失效的红包
	List<LuckbagInfo> selectluckbagBycreatetime();
}