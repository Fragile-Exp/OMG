package com.omg.comutting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.omg.cmn.DTO;

public class CommutingDaoImpl {
	
	/** LOG */
	final static Logger LOG = LoggerFactory.getLogger(CommutingDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<Commuting> rowMapper = new RowMapper<Commuting>() {

		@Override
		public Commuting mapRow(ResultSet rs, int rowNum) throws SQLException {
			Commuting outData = new Commuting();
			//수정해야함
			return outData;
		}
	};
	
	public CommutingDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public int doInsertAttendTime(DTO dto) {
		int flag=0;
		
		return flag;
	}
}
