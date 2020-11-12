package com.omg.organization.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.organization.domain.DeptVO;

@Repository("deptDao2")
public class DeptDaoImpl2 {
	static final Logger LOG = LoggerFactory.getLogger(DeptDaoImpl2.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper rowMapper = new RowMapper<DeptVO>() {

		@Override
		public DeptVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			DeptVO outVO = new DeptVO();
			outVO.setDeptNo(rs.getInt("dept_no"));
			outVO.setDeptNm(rs.getString("dept_nm"));
			outVO.setUpDept(rs.getInt("up_dept"));
			outVO.setLevel(rs.getInt("level"));
			return outVO;
		}
	};
	
	public DeptDaoImpl2(){}
	
	/**
	 * 부서체계 삭제
	 * @param DeptVO
	 * @return flag(1:성공)
	 */
	public int doDelete(DeptVO dept) {
		int flag = 0;
		// Param Setting
		Object[] args = {dept.getDeptNo()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM dept  				\n");
		sb.append("WHERE dept_no = ?			    \n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : "+dept);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(),args );
		LOG.debug(" 삭제 Flag : "+ flag);
		return flag;
	}
	
	/**
	 * 부서체계 추가
	 * @param DeptVO
	 * @return flag(1:성공)
	 */
	public int doInsert(DeptVO dept) {
		int flag = 0;
		// Param Setting
		Object[] args = {dept.getDeptNo(),dept.getDeptNm(),dept.getUpDept()!=0?dept.getUpDept():null};
		
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO dept (				\n");
		sb.append("	    dept_no,       	  	     	\n");
		sb.append("	    dept_nm,      	        	\n");
		sb.append("	    up_dept        	    		\n");
		sb.append("	) VALUES (             			\n");
		sb.append("	    ?,                 			\n");
		sb.append("	    ?,                 			\n");
		sb.append("	    ?                 			\n");
		sb.append("	)                     			\n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : \n"+dept);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug(" 등록 Flag : "+flag);
		
		return flag;
	}
	
	/**
	 * 부서체계 선택
	 * @param DeptVO
	 * @return DeptVO
	 */
	public DeptVO doSelectOne(DeptVO dept) {
		DeptVO outVO = null;
		// Param Setting
		Object[] args = {dept.getDeptNo()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT							\n");
		sb.append("	    dept_no,       	  	     	\n");
		sb.append("	    dept_nm,      	        	\n");
		sb.append("	    up_dept,       	    		\n");
		// 부서 목록을 위한 level 더미값 처리
		sb.append("	    1 as \"level\" 	    		\n");
		sb.append("FROM dept	            		\n");
		sb.append("WHERE							\n");
		sb.append("		dept_no = ?					\n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : \n"+dept);
		
		// Excute
		outVO = (DeptVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		LOG.debug(" 조회 VO : "+ outVO);
		
		return outVO;
	}
	
	/**
	 * 부서체계 수정
	 * @param DeptVO
	 * @return DeptVO
	 */
	public int doUpdate(DeptVO dept) {
		int flag = 0;
		
		// Param Setting
		Object[] args = {dept.getDeptNo(),
						dept.getDeptNm(),
						dept.getUpDept()!=0?dept.getUpDept():null,
						dept.getDeptNo()};
				
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE dept SET					\n");
		sb.append("	    dept_no = ?,         	  	\n");
		sb.append("	    dept_nm = ?,          	 	\n");
		sb.append("	    up_dept = ?        			\n");
		sb.append("	WHERE 		           			\n");
		sb.append("	    dept_no = ?    				\n");
		LOG.debug("param : \n"+dept);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug(" 수정 Flag : "+flag);
		return flag;
	}
	
	/**
	 * 부서체계 조회
	 * @param 
	 * @return List<DeptVO>
	 */
	public List<DeptVO> doSelectList() {

		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT							\n");
		sb.append("	    dept_no,            	  	\n");
		sb.append("	    dept_nm,        	      	\n");
		sb.append("	    up_dept,    	       		\n");
		sb.append("	    level 			       		\n");
		sb.append("FROM dept	            		\n");
		sb.append("START WITH up_dept is null		\n");
		sb.append("CONNECT BY prior 				\n");
		sb.append("		dept_no=up_dept				\n");
		LOG.debug("query : \n"+sb.toString());
		
		// Excute
		List<DeptVO> list = (List<DeptVO>) jdbcTemplate.query(sb.toString(), rowMapper);
		
		for(DeptVO vo:list) { 
			LOG.debug(" 조회 VO : "+ vo);
		}
		
		return list;
	}
	

}
