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
import org.springframework.stereotype.Repository;

import com.omg.schedule.domain.ScheduleCatVO;

@Repository("scheduleCatDao")
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
    
    /**
     * 카테고리 등록
     * @param schedulCatVO
     * @return flag
     * @author 박정민
     * @Date 2020-10-30
     */
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
    
    /**
     * 카테고리 삭제
     * @param categoryId
     * @return flag
     * @author 박정민
     * @Date 2020-10-30
     */
    @Override
    public int doDelete(int categoryId) {
	int flag = 0;
	StringBuilder sb = new StringBuilder();

	sb.append(" DELETE FROM schedule_cat \n");
	sb.append(" WHERE category_id = ?    \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + categoryId);
	LOG.debug("==============================");
	
	flag = jdbcTemplate.update(sb.toString(), categoryId);
	LOG.debug("flag: " + flag);
	
	return flag;
    }
    
    /**
     * 카테고리 수정
     * @param scheduleCatVO
     * @return flag
     * @author 박정민
     * @Date 2020-10-30
     */
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
	
	return flag;
    }
    
    /**
     * 카테고리 선택
     * @param categoryId
     * @return outVO
     * @author 박정민
     * @Date 2020-10-30
     */
    @Override
    public ScheduleCatVO doSelectOne(int categoryId) {
	ScheduleCatVO outVO = null;
	Object[] args = {categoryId};		
	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT category_id,   \n");
	sb.append("        category_nm    \n");
	sb.append(" FROM schedule_cat     \n");
	sb.append(" WHERE category_id = ? \n");
	
	LOG.debug("==============================");
	LOG.debug("= Parameter: " + categoryId);
	LOG.debug("==============================");
	
	outVO = (ScheduleCatVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
	
	LOG.debug("==============================");
	LOG.debug("= outVO: " + outVO);
	LOG.debug("==============================");

	return outVO;
    }

    /**
     * 카테고리 리스트
     * @return list
     * @author 박정민
     * @Date 2020-10-30
     */
    @Override
    public List<ScheduleCatVO> doSelectList() {
	List<ScheduleCatVO> list = null;
	StringBuilder sb = new StringBuilder();
	
	sb.append(" SELECT category_id, \n");
	sb.append("        category_nm  \n");
	sb.append(" FROM schedule_cat   \n");

	list = jdbcTemplate.query(sb.toString(), rowMapper);
	
	LOG.debug("==============================");
	for(ScheduleCatVO vo : list) {
	    LOG.debug("= VO: " + vo);
	}
	LOG.debug("==============================");
	
	return list;
    }
    
}
