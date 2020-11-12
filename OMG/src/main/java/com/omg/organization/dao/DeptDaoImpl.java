package com.omg.organization.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.organization.domain.DeptVO;

@Repository("deptDao")
public class DeptDaoImpl {
	static final Logger LOG = LoggerFactory.getLogger(DeptDaoImpl.class);
	private final String NAMESPACE = "com.omg.orgnization.dept";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
		
	public DeptDaoImpl(){}
	
	/**
	 * 부서체계 삭제 
	 * @param DeptVO
	 * @return flag(1:성공)
	 */
	public int doDelete(DeptVO dept) {
		LOG.debug("== doDelete ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doDelete";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.delete(statement,dept);

		LOG.debug(" 삭제 Flag : "+ flag);
		return flag;
	}
	
	/**
	 * 부서체계 추가
	 * @param DeptVO
	 * @return flag(1:성공)
	 */
	public int doInsert(DeptVO dept) {
		LOG.debug("== doInsert ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doInsert";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.insert(statement,dept);

		LOG.debug(" 등록 Flag : "+ flag);
		return flag;
	}
	
	/**
	 * 부서체계 선택
	 * @param DeptVO
	 * @return DeptVO
	 */
	public DeptVO doSelectOne(DeptVO dept) {
		LOG.debug("== doSelectOne ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		DeptVO outVO = sqlSessionTemplate.selectOne(statement,dept);
		LOG.debug(" 조회 VO : "+ outVO);
		
		return outVO;
	}
	
	/**
	 * 부서체계 수정
	 * @param DeptVO
	 * @return DeptVO
	 */
	public int doUpdate(DeptVO dept) {
		LOG.debug("== doUpdate ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doUpdate";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.update(statement,dept);
		LOG.debug(" 수정 Flag : "+flag);
		return flag;
	}
	
	/**
	 * 부서체계 조회
	 * @param 
	 * @return List<DeptVO>
	 */
	public List<DeptVO> doSelectList() {
		LOG.debug("== doSelectList ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectList";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		List<DeptVO> list = sqlSessionTemplate.selectList(statement);
		
		for(DeptVO vo:list) {
			LOG.debug(" 조회 VO : "+ vo);
		}
		
		return list;
	}
	

}
