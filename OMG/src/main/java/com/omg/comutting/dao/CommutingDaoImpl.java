package com.omg.comutting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.cmn.Criteria;
import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.domain.PresentState;
import com.omg.commuting.domain.State;

import oracle.net.ns.Communication;

@Repository("commutingDao")
public class CommutingDaoImpl implements CommutingDao {

	/** LOG */
	final static Logger LOG = LoggerFactory.getLogger(CommutingDaoImpl.class);
		
	/** namespace*/
	private final String NAMESPACE = "com.omg.commuting";
			
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	//Scheduler
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	
	public CommutingDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int doDelete(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doDelete=");
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE + ".doDelete";
		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		int verify = this.sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doSelectOne=");

		Commuting inVO = (Commuting) dto;
		 
		String statement = NAMESPACE + ".doSelectOne";
		Commuting outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("=selectone.param=" + inVO);
		LOG.debug("=outVO=" + outVO);

		if (null == outVO) {
			throw new EmptyResultDataAccessException(1);
		}

		LOG.debug("====================================");
		return outVO;
	}

	@Override
	public List<Commuting> doSelectList(Criteria criteria) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doSelectList=");

		String statement = NAMESPACE +".doSelectList";
		
		LOG.debug("=selectlist.param=" + criteria);
		LOG.debug("=statement=" + statement);
		List<Commuting> cList = this.sqlSessionTemplate.selectList(statement, criteria);

		LOG.debug("=cList=" + cList);

		LOG.debug("====================================");
		return cList;
	}
	
	@Override
	public int getTotalCount(Criteria criteria) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=getTotalCount=");
		
		String statement = NAMESPACE + ".getTotalCount";

		LOG.debug(">statement>" + statement);
		
		int verify = this.sqlSessionTemplate.selectOne(statement, criteria);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}

	@Override
	public int doInsert(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doInsert=");
		
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE + ".doInsert";

		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		
		int verify = this.sqlSessionTemplate.insert(statement, inVO);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}
	
	@Override
	public int doUpdate(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doUpdate=");
		
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE + ".doUpdate";

		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		
		int verify = this.sqlSessionTemplate.update(statement, inVO);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
		
		
	}
	
	@Override
	public int doUpdateWorkTime(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doUpdateWorkTime=");
		
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE + ".doUpdateWorkTime";

		LOG.debug(">statement>" + statement);
		LOG.debug(">param>" + inVO);
		
		int verify = this.sqlSessionTemplate.update(statement, inVO);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");

		return verify;
	}
	
	@Override
	public int doInit() {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doInit=");
		
		String statement = NAMESPACE + ".doInit";

		LOG.debug(">statement>" + statement);
		
		int verify = this.sqlSessionTemplate.insert(statement);

		LOG.debug("=verify=" + verify);
		LOG.debug("====================================");
		return verify;
	}
	
	@Override
	public List<Commuting> doSelectMyList(DTO dto) {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=doSelectMyList=");
		
		Commuting inVO = (Commuting) dto;
		String statement = NAMESPACE +".doSelectMyList";
		
		LOG.debug("=selectlist.param=" + inVO);
		LOG.debug("=statement=" + statement);
		List<Commuting> cList = this.sqlSessionTemplate.selectList(statement, inVO);

		LOG.debug("=cList=" + cList);

		LOG.debug("====================================");
		return cList;
	}
	
	@Override
	public List<Commuting> getAll() {
		LOG.debug("====================================");
		LOG.debug("=DAO=");
		LOG.debug("=getAll=");
		
		String statement = NAMESPACE +".getAll";
		
		LOG.debug("=statement=" + statement);
		List<Commuting> cList = this.sqlSessionTemplate.selectList(statement);
		LOG.debug("====================================");
		
		return cList;
	}
	
}
