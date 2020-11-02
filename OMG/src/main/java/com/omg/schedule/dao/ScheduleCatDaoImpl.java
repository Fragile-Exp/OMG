package com.omg.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.omg.schedule.domain.ScheduleCatVO;

public class ScheduleCatDaoImpl implements ScheduleCatDao {
    
    final static Logger LOG = LoggerFactory.getLogger(ScheduleCatDaoImpl.class);
    
    public ScheduleCatDaoImpl() {}
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    DataSource dataSource;
    
    RowMapper rowMapper = new RowMapper<ScheduleCatVO>() {
	@Override
	public ScheduleCatVO mapRow(ResultSet rs, int rowNum) throws SQLException{
	    ScheduleCatVO outVO = new ScheduleCatVO();
	    
	    outVO.setCategoryId(rs.getInt("category_id"));
	    outVO.setCategoryNm(rs.getString("category_nm"));
	    
	    return outVO;
	}
    };
    
    @Override
    public int doInsert(ScheduleCatVO scheduleCatVO) {
	int flag = 0;
	Object[] args = {
		scheduleCatVO.getCategoryId(),
		scheduleCatVO.getCategoryNm()
	};
	StringBuilder sb = new StringBuilder();
	
	sb.append(" INSERT INTO schedule_cat ( \n");
	sb.append("     category_id,           \n");
	sb.append("     category_nm            \n");
	sb.append(" ) VALUES (                 \n");
	sb.append("     ?,                     \n");
	sb.append("     ?                      \n");
	sb.append(" )                          \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleCatVO);
	LOG.debug("==============================");
	
	flag = jdbcTemplate.update(sb.toString(), args);
	LOG.debug("flag: " + flag);
	
	return flag;
    }
    
    @Override
    public int doDelete(ScheduleCatVO scheduleCatVO) {
	int flag = 0;
	Object arg = scheduleCatVO.getCategoryId();
	StringBuilder sb = new StringBuilder();

	sb.append(" DELETE FROM schedule_cat \n");
	sb.append(" WHERE category_id = ?    \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleCatVO);
	LOG.debug("==============================");
	
	flag = jdbcTemplate.update(sb.toString(), arg);
	LOG.debug("flag: " + flag);
	
	return 0;
    }
    
    @Override
    public int doUpdate(ScheduleCatVO scheduleCatVO) {
	int flag = 0;
	Object[] args = {
		scheduleCatVO.getCategoryNm(),
		scheduleCatVO.getCategoryId()
	};
	StringBuilder sb = new StringBuilder();
	
	sb.append(" UPDATE schedule_cat   \n");
	sb.append(" SET category_nm = ?   \n");
	sb.append(" WHERE category_id = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleCatVO);
	LOG.debug("==============================");
	
	flag = jdbcTemplate.update(sb.toString(), args);
	
	return 0;
    }
    
    @Override
    public ScheduleCatVO doSelectOne(ScheduleCatVO scheduleCatVO) {
	ScheduleCatVO outVO = null;
	Object[] args = {scheduleCatVO.getCategoryId()};		
	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT category_id,   \n");
	sb.append("        category_nm    \n");
	sb.append(" FROM schedule_cat     \n");
	sb.append(" WHERE category_id = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + scheduleCatVO);
	LOG.debug("==============================");
	
	outVO = (ScheduleCatVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
	
	LOG.debug("==============================");
	LOG.debug("= outVO: " + outVO);
	LOG.debug("==============================");

	return outVO;
    }

    @Override
    public List<ScheduleCatVO> doSelectList() {
	List<ScheduleCatVO> list = null;
	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT category_id, \n");
	sb.append("        category_nm  \n");
	sb.append(" FROM schedule_cat   \n");
	
	
	
	return list;
    }
    
}
