package com.omg.comments.service;

import java.util.List;

import com.omg.cmn.Search;
import com.omg.comments.domain.CommentsVO;

public interface CommentsService 
{
public int doInsert(CommentsVO board);
	
	public int doDelete(CommentsVO board);
	
	public int doUpdate(CommentsVO board);
	
	public CommentsVO doSelectOne(CommentsVO board);
	
	public List<CommentsVO> doSelectList(Search search);
}
