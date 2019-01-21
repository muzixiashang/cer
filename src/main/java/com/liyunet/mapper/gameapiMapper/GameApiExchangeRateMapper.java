package com.liyunet.mapper.gameapiMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.gameapi.GameApiExchangeRate;
import com.liyunet.domain.gameapi.GameApiExchangeRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface GameApiExchangeRateMapper {
    int countByExample(GameApiExchangeRateExample example);

    int deleteByExample(GameApiExchangeRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameApiExchangeRate record);

    int insertSelective(GameApiExchangeRate record);

    List<GameApiExchangeRate> selectByExample(GameApiExchangeRateExample example);

    GameApiExchangeRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameApiExchangeRate record, @Param("example") GameApiExchangeRateExample example);

    int updateByExample(@Param("record") GameApiExchangeRate record, @Param("example") GameApiExchangeRateExample example);

    int updateByPrimaryKeySelective(GameApiExchangeRate record);

    int updateByPrimaryKey(GameApiExchangeRate record);
}