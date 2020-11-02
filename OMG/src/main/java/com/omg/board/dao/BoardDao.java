package com.omg.board.dao;

import java.util.List;

import com.omg.board.domain.BoardVO;
import com.omg.cmn.Search;

public interface BoardDao 
{
	public int doInsert(BoardVO board);
	
	public int doDelete(BoardVO board);
	
	public int doUpdate(BoardVO board);
	
	public BoardVO doSelectOne(int board_seq);
	
	public List<BoardVO> doSelectList(Search search);
	
	
}
