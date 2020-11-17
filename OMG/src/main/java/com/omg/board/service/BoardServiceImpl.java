package com.omg.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.board.dao.BoardDaoImpl;
import com.omg.board.domain.BoardVO;
import com.omg.cmn.Search;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService
{
	final Logger LOG = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDaoImpl  boardDao;
	 
	public BoardServiceImpl() {}
	
	@Override
	public int doInsert(BoardVO board)
	{
		return boardDao.doInsert(board);
	}
	
	@Override
	public int doDelete(BoardVO board)
	{
		return boardDao.doDelete(board);
	}
	
	@Override
	public int doUpdate(BoardVO board)
	{
		return boardDao.doUpdate(board);
	}
	
	@Override
	public BoardVO doSelectOne(BoardVO board)
	{
		boardDao.readCount(board);
		
		return boardDao.doSelectOne(board);
	}
	
	@Override
	public List<BoardVO> doSelectList(Search search)
	{
		return boardDao.doSelectList(search);
	}
	
}

