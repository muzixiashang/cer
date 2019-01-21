package com.liyunet.mapper.luckbagMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.luckbag.LuckbagBlessingInfo;
import com.liyunet.domain.luckbag.LuckbagBlessingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface LuckbagBlessingInfoMapper {
    int countByExample(LuckbagBlessingInfoExample example);

    int deleteByExample(LuckbagBlessingInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LuckbagBlessingInfo record);

    int insertSelective(LuckbagBlessingInfo record);

    List<LuckbagBlessingInfo> selectByExample(LuckbagBlessingInfoExample example);

    LuckbagBlessingInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LuckbagBlessingInfo record, @Param("example") LuckbagBlessingInfoExample example);

    int updateByExample(@Param("record") LuckbagBlessingInfo record, @Param("example") LuckbagBlessingInfoExample example);

    int updateByPrimaryKeySelective(LuckbagBlessingInfo record);

    int updateByPrimaryKey(LuckbagBlessingInfo record);
}