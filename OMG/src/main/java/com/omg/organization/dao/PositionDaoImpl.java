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

import com.omg.organization.domain.PositionVO;

@Repository("positionDao")
public class PositionDaoImpl {
	static final Logger LOG = LoggerFactory.getLogger(PositionDaoImpl.class);
	
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
	
	public PositionDaoImpl() {}
	
	public int doDelete(PositionVO position) {
		int flag = 0;
		return flag;
	}
	
	public int doInsert(PositionVO position) {
		int flag = 0;
		return flag;
	}

	public int doSelectOne(PositionVO position) {
		int flag = 0;
		return flag;
	}
	
	public int doUpdate(PositionVO position) {
		int flag = 0;
		return flag;
	}
	
	public List<PositionVO> doSelectList() {
		List<PositionVO> list = null;
		return list;
	}
}
