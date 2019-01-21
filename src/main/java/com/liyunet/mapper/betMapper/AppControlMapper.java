package com.liyunet.mapper.betMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.bet.AppControl;
import com.liyunet.domain.bet.AppControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface AppControlMapper {
    int countByExample(AppControlExample example);

    int deleteByExample(AppControlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppControl record);

    int insertSelective(AppControl record);

    List<AppControl> selectByExample(AppControlExample example);

    AppControl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppControl record, @Param("example") AppControlExample example);

    int updateByExample(@Param("record") AppControl record, @Param("example") AppControlExample example);

    int updateByPrimaryKeySelective(AppControl record);

    int updateByPrimaryKey(AppControl record);
}