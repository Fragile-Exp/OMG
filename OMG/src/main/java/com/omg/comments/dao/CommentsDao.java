package com.omg.comments.dao;

import java.util.List;

import com.omg.cmn.Search;
import com.omg.comments.domain.CommentsVO;

public interface CommentsDao 
{
	public int doInsert(CommentsVO Comments);
	
	public int doDelete(CommentsVO Comments);
	
	public int doUpdate(CommentsVO Comments);
	
	public CommentsVO doSelectOne(CommentsVO Comments);
	
	public List<CommentsVO> doSelectList(Search search);
}
