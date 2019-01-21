package com.liyunet.mapper.hkMapper;

import com.liyunet.MybatisMapper;
import com.liyunet.domain.hk.CoinHkProjet;
import com.liyunet.domain.hk.CoinHkProjetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@MybatisMapper
public interface CoinHkProjetMapper {
	int countByExample(CoinHkProjetExample example);

	int deleteByExample(CoinHkProjetExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(CoinHkProjet record);

	int insertSelective(CoinHkProjet record);

	List<CoinHkProjet> selectByExample(CoinHkProjetExample example);

	CoinHkProjet selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") CoinHkProjet record, @Param("example") CoinHkProjetExample example);

	int updateByExample(@Param("record") CoinHkProjet record, @Param("example") CoinHkProjetExample example);

	int updateByPrimaryKeySelective(CoinHkProjet record);

	int updateByPrimaryKey(CoinHkProjet record);

	// 获取所有投资记录
	List<CoinHkProjet> getHKlist(@Param("page") Integer page, @Param("rows") Integer rows);
}