package com.omg.readcnt.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.readcnt.domain.ReadCntVO;

@Repository("readCntDao")
public class ReadCntDaoImpl {
	static final Logger LOG = LoggerFactory.getLogger(ReadCntDaoImpl.class);
	private final String NAMESPACE = "com.omg.readCnt";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
		
	public ReadCntDaoImpl(){}
	
	/**
	 * 조회 추가
	 * @param ReadCntVO
	 * @return flag(1:성공)
	 */
	public int doInsert(ReadCntVO cntVO) {
		LOG.debug("== doInsert ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doInsert";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.insert(statement,cntVO);

		LOG.debug(" 등록 Flag : "+ flag);
		return flag;
	}
	
	/**
	 * 확인
	 * @param ReadCntVO
	 * @return 
	 */
	public int doSelectOne(ReadCntVO cntVO) {
		LOG.debug("== doSelectOne ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.selectOne(statement,cntVO);
		LOG.debug(" 조회 flag(있는지 여부) : "+ flag);
		
		return flag;
	}
	

}
