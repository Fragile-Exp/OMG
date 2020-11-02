package com.omg.board.service;

import java.util.List;

import com.omg.board.domain.BoardVO;
import com.omg.cmn.Search;

public interface BoardService 
{
	public int doInsert(BoardVO board);
	
	public int doDelete(BoardVO board);
	
	public int doUpdate(BoardVO board);
	
	public BoardVO doSelectOne(int board_seq);
	
	public List<BoardVO> doSelectList(Search search);
}
