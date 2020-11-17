package com.omg.comments.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.cmn.Search;
import com.omg.comments.dao.CommentsDaoImpl;
import com.omg.comments.domain.CommentsVO;

@Service("CommentsServiceImpl")
public class CommentsServiceImpl implements CommentsService
{
	
	final Logger LOG = LoggerFactory.getLogger(CommentsServiceImpl.class);
	
	@Autowired
	private CommentsDaoImpl  commentsDao;
	
	public CommentsServiceImpl() {}

	@Override
	public int doInsert(CommentsVO comments) 
	{	
		return commentsDao.doInsert(comments);
	}

	@Override
	public int doDelete(CommentsVO comments) 
	{
		return commentsDao.doDelete(comments);
	}
	
	@Override
	public int doDeleteOne(CommentsVO comments) 
	{
		return commentsDao.doDeleteOne(comments);
	}
	
	@Override
	public int doCmtCnt(CommentsVO comments) 
	{
		return commentsDao.doCmtCnt(comments);
	}

	@Override
	public int doUpdate(CommentsVO comments) 
	{
		return commentsDao.doUpdate(comments);
	}

	@Override
	public CommentsVO doSelectOne(CommentsVO comments) 
	{
		return commentsDao.doSelectOne(comments);
	}

	@Override
	public List<CommentsVO> doSelectList(CommentsVO comments) 
	{
		return commentsDao.doSelectList(comments);
	}

}
