package com.liyunet.mapper.gameapiMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.gameapi.GameApiInfo;
import com.liyunet.domain.gameapi.GameApiInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface GameApiInfoMapper {
    int countByExample(GameApiInfoExample example);

    int deleteByExample(GameApiInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameApiInfo record);

    int insertSelective(GameApiInfo record);

    List<GameApiInfo> selectByExample(GameApiInfoExample example);

    GameApiInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameApiInfo record, @Param("example") GameApiInfoExample example);

    int updateByExample(@Param("record") GameApiInfo record, @Param("example") GameApiInfoExample example);

    int updateByPrimaryKeySelective(GameApiInfo record);

    int updateByPrimaryKey(GameApiInfo record);
}