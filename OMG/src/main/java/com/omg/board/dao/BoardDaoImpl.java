package com.omg.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.board.domain.BoardVO;
import com.omg.cmn.Search;

@Repository
public class BoardDaoImpl
{
	final static Logger LOG = LoggerFactory.getLogger(BoardDaoImpl.class);
	
	
	/** NAMESPACE */
	private final String NAMESPACE = "com.omg.board";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	public BoardDaoImpl() {}
	
	/**
	 * 조회 count증가
	 * @param boardVO
	 * @return int
	 */
	public int readCount(BoardVO boardVO) 
	{
		LOG.debug("=====================");
		LOG.debug("=readCount=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.sist.ehr.board.readCount
		String statement = NAMESPACE +".readCount";		
		LOG.debug("=statement="+statement);		
		LOG.debug("=param="+boardVO);	
		int flag = this.sqlSessionTemplate.update(statement, boardVO);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	public List<BoardVO> doSelectList(Search search)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectList=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectList";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=search : "+search);
		LOG.debug("===========================");
		
		List<BoardVO> list = this.sqlSessionTemplate.selectList(statement, search);
		
		for(BoardVO vo : list)
		{
			LOG.debug("vo : "+vo);
		}
		
		return list;
	}
	
	
	public BoardVO doSelectOne(BoardVO boardVO)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectOne";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=boardVO : "+boardVO);
		LOG.debug("===========================");
		
		BoardVO outVO = this.sqlSessionTemplate.selectOne(statement, boardVO);
		
		return outVO;
	}
	
	
	public int doUpdate(BoardVO boardVO)
	{
		LOG.debug("===========================");
		LOG.debug("=doUpdate=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doUpdate";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=boardVO : "+boardVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.update(statement, boardVO);
		
		return flag;
	}
	
	
	public int doDelete(BoardVO boardVO)
	{
		LOG.debug("===========================");
		LOG.debug("=doDelete=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doDelete";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=boardVO : "+boardVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.delete(statement, boardVO);
		LOG.debug("=flag : "+flag);
		
		return flag;
	}
	
	/**
	 * 게시판 등록
	 * @param board
	 * @return
	 */
	
	public int doInsert(BoardVO boardVO)
	{
		LOG.debug("===========================");
		LOG.debug("=doInsert=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doInsert";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=boardVO : "+boardVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.insert(statement, boardVO);
		
		return flag;
	}
}
