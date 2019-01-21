package com.liyunet.mapper.hkMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.hk.CoinHkContract;
import com.liyunet.domain.hk.CoinHkContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface CoinHkContractMapper {
    int countByExample(CoinHkContractExample example);

    int deleteByExample(CoinHkContractExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoinHkContract record);

    int insertSelective(CoinHkContract record);

    List<CoinHkContract> selectByExample(CoinHkContractExample example);

    CoinHkContract selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoinHkContract record, @Param("example") CoinHkContractExample example);

    int updateByExample(@Param("record") CoinHkContract record, @Param("example") CoinHkContractExample example);

    int updateByPrimaryKeySelective(CoinHkContract record);

    int updateByPrimaryKey(CoinHkContract record);
}