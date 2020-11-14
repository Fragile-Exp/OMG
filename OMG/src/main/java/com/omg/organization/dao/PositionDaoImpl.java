package com.omg.organization.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.organization.domain.PositionVO;

@Repository("positionDao")
public class PositionDaoImpl {
	static final Logger LOG = LoggerFactory.getLogger(PositionDaoImpl.class);
	private final String NAMESPACE = "com.omg.orgnization.position";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
		
	public PositionDaoImpl() {}
	
	/**
	 * 사원 직급체계 삭제
	 * @param position
	 * @return flag(1:성공)
	 */
	public int doDelete(PositionVO position) {
		LOG.debug("== doDelete ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doDelete";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.delete(statement,position);

		LOG.debug(" 삭제 Flag : "+ flag);
		return flag;
	}
	
	/**
	 * 사원 직급체계 추가
	 * @param position
	 * @return flag(1:성공)
	 */
	public int doInsert(PositionVO position) {
		LOG.debug("== doInsert ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doInsert";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.insert(statement,position);

		LOG.debug(" 등록 Flag : "+ flag);
		return flag;
	}

	/**
	 * 사원 직급체계 선택
	 * @param position
	 * @return PositionVO
	 */
	public PositionVO doSelectOne(PositionVO position) {
		LOG.debug("== doSelectOne ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		PositionVO outVO = sqlSessionTemplate.selectOne(statement,position);
		LOG.debug(" 조회 VO : "+ outVO);
		
		return outVO;
	}
	
	/**
	 * 사원 직급체계 수정
	 * @param position
	 * @return PositionVO
	 */
	public int doUpdate(PositionVO position) {
		LOG.debug("== doUpdate ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doUpdate";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.update(statement,position);
		LOG.debug(" 수정 Flag : "+flag);
		return flag;
	}
	
	/**
	 * 사원 직급체계 조회
	 * @param position
	 * @return PositionVO
	 */
	public List<PositionVO> doSelectList() {
		LOG.debug("== doSelectList ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectList";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		List<PositionVO> list = sqlSessionTemplate.selectList(statement);
		
		for(PositionVO vo:list) {
			LOG.debug(" 조회 VO : "+ vo);
		} 
		
		return list;
	}
}
