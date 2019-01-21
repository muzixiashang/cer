package com.liyunet.mapper.hkMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.hk.CoinHkOrder;
import com.liyunet.domain.hk.CoinHkOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
@MybatisMapper
public interface CoinHkOrderMapper {
    int countByExample(CoinHkOrderExample example);

    int deleteByExample(CoinHkOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoinHkOrder record);

    int insertSelective(CoinHkOrder record);

    List<CoinHkOrder> selectByExample(CoinHkOrderExample example);

    CoinHkOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoinHkOrder record, @Param("example") CoinHkOrderExample example);

    int updateByExample(@Param("record") CoinHkOrder record, @Param("example") CoinHkOrderExample example);

    int updateByPrimaryKeySelective(CoinHkOrder record);

    int updateByPrimaryKey(CoinHkOrder record);
}