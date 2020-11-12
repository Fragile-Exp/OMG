package com.omg.organization.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.organization.domain.PositionVO;

@Repository("positionDao2")
public class PositionDaoImpl2 {
	static final Logger LOG = LoggerFactory.getLogger(PositionDaoImpl2.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper rowMapper = new RowMapper<PositionVO>() {

		@Override
		public PositionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			PositionVO outVO = new PositionVO();
			outVO.setPositionNo(rs.getInt("position_no"));
			outVO.setPositionNm(rs.getString("position_nm"));
			outVO.setUpPosition(rs.getInt("up_position"));
			return outVO;
		}
		
	};
	
	public PositionDaoImpl2() {}
	
	/**
	 * 사원 직급체계 삭제
	 * @param position
	 * @return flag(1:성공)
	 */
	public int doDelete(PositionVO position) {
		int flag = 0;
		// Param Setting
		Object[] args = {position.getPositionNo()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM position				\n");
		sb.append("WHERE position_no = ?			\n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : "+position);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(),args );
		LOG.debug(" 삭제 Flag : "+ flag);
		return flag;
	}
	
	/**
	 * 사원 직급체계 추가
	 * @param position
	 * @return flag(1:성공)
	 */
	public int doInsert(PositionVO position) {
		int flag = 0;
		// Param Setting
		Object[] args = {position.getPositionNo(),position.getPositionNm(),position.getUpPosition()!=0?position.getUpPosition():null};
		
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO position (			\n");
		sb.append("	    position_no,              	\n");
		sb.append("	    position_nm,              	\n");
		sb.append("	    up_position            		\n");
		sb.append("	) VALUES (             			\n");
		sb.append("	    ?,                 			\n");
		sb.append("	    ?,                 			\n");
		sb.append("	    ?                 			\n");
		sb.append("	)                     			\n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : \n"+position);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug(" 등록 Flag : "+flag);
		
		return flag;
	}

	/**
	 * 사원 직급체계 선택
	 * @param position
	 * @return PositionVO
	 */
	public PositionVO doSelectOne(PositionVO position) {
		PositionVO outVO = null;
		// Param Setting
		Object[] args = {position.getPositionNo()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT							\n");
		sb.append("	    position_no,              	\n");
		sb.append("	    position_nm,              	\n");
		sb.append("	    up_position            		\n");
		sb.append("FROM position            		\n");
		sb.append("WHERE							\n");
		sb.append("		position_no = ?				\n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : \n"+position);
		
		// Excute
		outVO = (PositionVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		LOG.debug(" 조회 VO : "+ outVO);
		
		return outVO;
	}
	
	/**
	 * 사원 직급체계 수정
	 * @param position
	 * @return PositionVO
	 */
	public int doUpdate(PositionVO position) {
		int flag = 0;
		
		// Param Setting
		Object[] args = {position.getPositionNo(),
						position.getPositionNm(),
						position.getUpPosition()!=0?position.getUpPosition():null,
						position.getPositionNo()};
				
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE position SET				\n");
		sb.append("	    position_no = ?,           	\n");
		sb.append("	    position_nm = ?,           	\n");
		sb.append("	    up_position = ?        		\n");
		sb.append("	WHERE 		           			\n");
		sb.append("	    position_no = ?    			\n");
		LOG.debug("param : \n"+position);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug(" 수정 Flag : "+flag);
		return flag;
	}
	
	/**
	 * 사원 직급체계 조회
	 * @param position
	 * @return PositionVO
	 */
	public List<PositionVO> doSelectList() {

		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT							\n");
		sb.append("	    position_no,              	\n");
		sb.append("	    position_nm,              	\n");
		sb.append("	    up_position            		\n");
		sb.append("FROM position            		\n");
		sb.append("START WITH up_position is null	\n");
		sb.append("CONNECT BY prior 				\n");
		sb.append("		position_no=up_position		\n");
		LOG.debug("query : \n"+sb.toString());
		
		// Excute 
		List<PositionVO> list = (List<PositionVO>) jdbcTemplate.query(sb.toString(), rowMapper);
		
		for(PositionVO vo:list) {
			LOG.debug(" 조회 VO : "+ vo);
		}
		
		return list;
	}
}
