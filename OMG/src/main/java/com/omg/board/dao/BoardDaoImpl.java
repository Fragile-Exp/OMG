package com.omg.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.board.domain.BoardVO;
import com.omg.cmn.Search;

@Repository("BoardDao")
public class BoardDaoImpl implements BoardDao
{
	final static Logger LOG = LoggerFactory.getLogger(BoardDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper rowMapper = new RowMapper<BoardVO>() 
	{
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			BoardVO outVO = new BoardVO();
		
			outVO.setBoardSeq(rs.getInt("BOARD_SEQ"));
			outVO.setDiv(rs.getString("DIV"));
			outVO.setTitle(rs.getString("TITLE"));   
			outVO.setContents(rs.getString("CONTENTS"));
			outVO.setRead_cnt(rs.getInt("READ_CNT"));
			outVO.setFilecode(rs.getInt("FILE_CODE"));
			outVO.setRegDt(rs.getString("REG_DT"));
		
			return outVO;
		}
	};
	
	public BoardDaoImpl() {}
	
	@Override
	public List<BoardVO> doSelectList(Search search)
	{
		List<BoardVO> list = new ArrayList();
		
		StringBuilder sbWhere=new StringBuilder();
		//검색구분 != null !"".equals(검색구분)
		if(null != search.getSearchDiv() && !"".equals(search.getSearchDiv()))
		{
			if("10".equals(search.getSearchDiv()))
			{
				sbWhere.append("WHERE title like '%'|| ? ||'%' \n");
			}
			else if("20".equals(search.getSearchDiv()))
			{
				sbWhere.append("WHERE contents like '%'|| ? ||'%' \n");
			}
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT T1.*,T2.*                                                                       \n");
		sb.append("FROM                                                                                   \n");
		sb.append("(                                                                                      \n");
		sb.append("    SELECT B.rnum,                                                                     \n");
		sb.append("           B.board_seq,                                                                \n");  
		sb.append("           B.div,                                                                      \n");
		sb.append("           B.title,                                                                    \n");
		sb.append("           B.contents,                                                                 \n");
		sb.append("           B.read_cnt,                                                                 \n");
		sb.append("           B.file_code,                                                                \n");
		sb.append("           DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(B.reg_dt,'YYYYMMDD')             \n");
		sb.append("                                             ,TO_CHAR(B.reg_dt,'HH24:MI')              \n");
		sb.append("                                             ,TO_CHAR(B.reg_dt,'YYYY-MM-DD')) reg_dt,  \n");                                                           
		sb.append("           B.reg_id,                                                                   \n");
		sb.append("           DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(B.mod_dt,'YYYYMMDD')             \n");
		sb.append("                                             ,TO_CHAR(B.mod_dt,'HH24:MI')              \n");
		sb.append("                                             ,TO_CHAR(B.mod_dt,'YYYY-MM-DD')) mod_dt,  \n");
		sb.append("		   B.mod_id                                                                       \n");
		sb.append("    FROM                                                                               \n");
		sb.append("    (                                                                                  \n");
		sb.append("        SELECT ROWNUM rnum, A.*                                                        \n");
		sb.append("        FROM                                                                           \n");
		sb.append("        (                                                                              \n");
		sb.append("            SELECT *                                                                   \n");
		sb.append("            FROM board                                                                 \n");                                       
		sb.append("            --WHERE 조건                                                                \n");
		
		//--------------------------------------------------------------------------------------------------
	    // WHERE 조건
	    //--------------------------------------------------------------------------------------------------
	    sb.append(sbWhere.toString());
	    
		sb.append("            ORDER BY reg_dt DESC                                                       \n");
		sb.append("        ) A                                                                            \n");
		sb.append("        --WHERE ROWNUM <= (&PAGE_SIZE * (&PAGE_NUM-1)+&PAGE_SIZE)                      \n");
		sb.append("        WHERE ROWNUM <= (? * (?-1)+?)                                                  \n");
		sb.append("    )B                                                                                 \n");
		sb.append("    --WHERE b.rnum >= (&PAGE_SIZE * (&PAGE_NUM-1) + 1)                                 \n");
		sb.append("    WHERE b.rnum >= (? * (?-1) + 1)                                                    \n");
		sb.append(") T1                                                                                   \n");
		sb.append("CROSS JOIN                                                                             \n");
		sb.append("(                                                                                      \n");
		sb.append("    SELECT COUNT(*) total_cnt                                                          \n");
		sb.append("    FROM board                                                                         \n");
		sb.append("    --WHERE 조건                                                                         \n");
		
		//--------------------------------------------------------------------------------------------------
	    // WHERE 조건
	    //--------------------------------------------------------------------------------------------------
	    sb.append(sbWhere.toString());
	    
		sb.append(") T2	                                                                                  \n");
		
		//Param처리
	    List<Object> listArg = new ArrayList<Object>();
	    
	    //검색조건+ : 7개 ?
	    if(null != search.getSearchDiv() && !"".equals(search.getSearchDiv()))
	    {
	    	listArg.add(search.getSearchWord());
	    	listArg.add(search.getPageSize());
	    	listArg.add(search.getPageNum());
	    	listArg.add(search.getPageSize());
	    	listArg.add(search.getPageSize());
	    	listArg.add(search.getPageNum());
	    	listArg.add(search.getSearchWord());
	    }
	    else
	    {
	    	listArg.add(search.getPageSize());
	    	listArg.add(search.getPageNum());
	    	listArg.add(search.getPageSize());
	    	listArg.add(search.getPageSize());
	    	listArg.add(search.getPageNum());
	    }
	    
	    list = jdbcTemplate.query(sb.toString(), listArg.toArray(), rowMapper);
	    for(BoardVO vo : list)
	    {
	    	LOG.debug("====================================");
			LOG.debug("=vo="+vo);
			LOG.debug("====================================");
	    }
		
		return list;
	}
	
	@Override
	public BoardVO doSelectOne(BoardVO board)
	{
		BoardVO outVO = null;
		Object args[] = {board.getBoardSeq()};
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT                                                 \n");
		sb.append("    board_seq,                                         \n");
		sb.append("    div,                                               \n");
		sb.append("    title,                                             \n");
		sb.append("    contents,                                          \n");
		sb.append("    read_cnt,                                          \n");
		sb.append("    file_code,                                         \n");
		sb.append("    TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt,   \n");
		sb.append("    reg_id,                                            \n");
		sb.append("    TO_CHAR(mod_dt,'YYYY-MM-DD HH24MISS') AS mod_dt,   \n");
		sb.append("    mod_id,                                            \n");
		sb.append("    1 AS rnum,                                         \n");
		sb.append("    1 AS total_cnt                                     \n");
		sb.append("FROM board                                             \n");
		sb.append("WHERE board_seq = ?                                    \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+board);
		LOG.debug("========================");
		
		
		outVO = (BoardVO) this.jdbcTemplate.queryForObject(sb.toString(), 
	                   args, 
	                   rowMapper);
		
		LOG.debug("========================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("========================");
		
		return outVO;
	}
	
	@Override
	public int doUpdate(BoardVO board)
	{
		int flag = 0;
		Object[]  args = { 
							
						    board.getDiv(),
						    board.getTitle(),
						    board.getContents(),
						    board.getRead_cnt(),
						    board.getFilecode(),
						    board.getModId(),
						    board.getBoardSeq()
						 };
		
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE board          \n");
		sb.append("SET div = ?,          \n");
		sb.append("    title = ?,        \n");
		sb.append("    contents = ?,     \n");
		sb.append("    read_cnt = ?,     \n");
		sb.append("    file_code = ?,    \n");
		sb.append("    mod_dt = SYSDATE, \n");
		sb.append("    mod_id = ?        \n");
		sb.append("WHERE board_seq = ?   \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+board);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	@Override
	public int doDelete(BoardVO board)
	{
		int flag = 0;
		Object[] args = { board.getBoardSeq()};
		
		StringBuilder  sb=new StringBuilder();
		sb.append("DELETE FROM board   \n");
		sb.append("WHERE board_seq = ? \n");
		
		LOG.debug("========================");
		LOG.debug("=param=\n"+board);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	/**
	 * 게시판 등록
	 * @param board
	 * @return
	 */
	@Override
	public int doInsert(BoardVO board)
	{
		int flag =0;
		Object[]  args = { 
							//board.getBoardSeq(),
						    board.getDiv(),
						    board.getTitle(),
						    board.getContents(),
						    board.getRead_cnt(),
						    //board.getFilecode(),
						    board.getRegId(),
						    board.getModId()
						 };
		
		StringBuilder  sb=new StringBuilder();
		sb.append("INSERT INTO board (         \n");
		sb.append("    board_seq,              \n");
		sb.append("    div,                    \n");
		sb.append("    title,                  \n");
		sb.append("    contents,               \n");
		sb.append("    read_cnt,               \n");
		sb.append("    file_code,              \n");
		sb.append("    reg_dt,                 \n");
		sb.append("    reg_id,                 \n");
		sb.append("    mod_dt,                 \n");
		sb.append("    mod_id                  \n");
		sb.append(") VALUES (                  \n");
		sb.append("    BOARD_SEQ.NEXTVAL,      \n");
		sb.append("    ?,                      \n");
		sb.append("    ?,                      \n");
		sb.append("    ?,                      \n");
		sb.append("    ?,                      \n");
		sb.append("    ATTACHMENT_SEQ.NEXTVAL, \n");
		sb.append("    SYSDATE,                \n");
		sb.append("    ?,                      \n");
		sb.append("    SYSDATE,                \n");
		sb.append("    ?                       \n");
		sb.append(")                           \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+board);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
}
