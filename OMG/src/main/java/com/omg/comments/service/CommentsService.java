package com.omg.comments.service;

import java.util.List;

import com.omg.cmn.Search;
import com.omg.comments.domain.CommentsVO;

public interface CommentsService 
{
public int doInsert(CommentsVO comments);
	
	public int doDelete(CommentsVO comments);
	
	public int doDeleteOne(CommentsVO comments);
	
	public int doCmtCnt(CommentsVO comments);
	
	public int doUpdate(CommentsVO comments);
	
	public CommentsVO doSelectOne(CommentsVO comments);
	
	public List<CommentsVO> doSelectList(CommentsVO comments);
}
