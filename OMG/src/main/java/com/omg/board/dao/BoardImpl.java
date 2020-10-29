package com.omg.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BoardImpl 
{
	final static Logger LOG = LoggerFactory.getLogger(BoardImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper rowMapper = new RowMapper<Board>() 
	{
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Board outVO = new Board();
		
			outVO.setBoard_seq(rs.getString("BOARD_SEQ"));
			outVO.setDiv(rs.getString("DIV"));
			outVO.setTitle(rs.getString("TITLE"));   
			outVO.setContents(rs.getString("CONTENTS"));
			outVO.setRead_cnt(rs.getInt("READ_CNT"));
			outVO.setRegDt(rs.getString("REG_DT"));
		
			return outVO;
		}
	};
	
	public BoardImpl() {}
	
	public int doInsert(Board board)
	{
		int flag =0;
		
		return flag;
	}
}
