package com.omg.comments.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.cmn.Search;
import com.omg.comments.domain.CommentsVO;

@Repository("CommentsDao")
public class CommentsDaoImpl implements CommentsDao
{
	final static Logger LOG = LoggerFactory.getLogger(CommentsDaoImpl.class);
	
	/** NAMESPACE */
	private final String NAMESPACE = "com.omg.comments";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public CommentsDaoImpl() {}
	
	@Override
	public List<CommentsVO> doSelectList(Search search) 
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectList=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectList";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=param : "+search);
		LOG.debug("===========================");
		
		List<CommentsVO> list = this.sqlSessionTemplate.selectList(statement, search);
		
		for(CommentsVO vo : list)
		{
			LOG.debug("vo : "+vo);
		}
		
		return list;
	}

	

	@Override
	public CommentsVO doSelectOne(CommentsVO CommentsVO) 
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectOne";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=CommentsVO : "+CommentsVO);
		LOG.debug("===========================");
		
		CommentsVO outVO = this.sqlSessionTemplate.selectOne(statement, CommentsVO);
		
		return outVO;
	}
	
	@Override
	public int doUpdate(CommentsVO CommentsVO) 
	{
		LOG.debug("===========================");
		LOG.debug("=doUpdate=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doUpdate";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=CommentsVO : "+CommentsVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.update(statement, CommentsVO);
		
		return flag;
	}
	
	@Override
	public int doDelete(CommentsVO CommentsVO) 
	{
		LOG.debug("===========================");
		LOG.debug("=doDelete=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doDelete";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=CommentsVO : "+CommentsVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.delete(statement, CommentsVO);
		LOG.debug("=flag : "+flag);
		
		return flag;
	}
	
	@Override
	public int doInsert(CommentsVO CommentsVO) 
	{
		LOG.debug("===========================");
		LOG.debug("=doInsert=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doInsert";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=CommentsVO : "+CommentsVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.insert(statement, CommentsVO);
		
		return flag;
	}

	

}
