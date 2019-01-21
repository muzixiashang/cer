package com.liyunet.mapper.gameapiMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.gameapi.GameApiRoleInfo;
import com.liyunet.domain.gameapi.GameApiRoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface GameApiRoleInfoMapper {
    int countByExample(GameApiRoleInfoExample example);

    int deleteByExample(GameApiRoleInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameApiRoleInfo record);

    int insertSelective(GameApiRoleInfo record);

    List<GameApiRoleInfo> selectByExample(GameApiRoleInfoExample example);

    GameApiRoleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameApiRoleInfo record, @Param("example") GameApiRoleInfoExample example);

    int updateByExample(@Param("record") GameApiRoleInfo record, @Param("example") GameApiRoleInfoExample example);

    int updateByPrimaryKeySelective(GameApiRoleInfo record);

    int updateByPrimaryKey(GameApiRoleInfo record);
}