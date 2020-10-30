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

import com.omg.cmn.Search;

public class BoardDao 
{
	final static Logger LOG = LoggerFactory.getLogger(BoardDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper rowMapper = new RowMapper<Board>() 
	{
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Board outVO = new Board();
		
			outVO.setBoard_seq(rs.getString("BOARD_SEQ"));
			outVO.setDiv(rs.getString("DIV"));
			outVO.setTitle(rs.getString("TITLE"));   
			outVO.setContents(rs.getString("CONTENTS"));
			outVO.setRead_cnt(rs.getInt("READ_CNT"));
			outVO.setRegDt(rs.getString("REG_DT"));
		
			return outVO;
		}
	};
	
	public BoardDao() {}
	
	public List<Board> doSelectList(Search search)
	{
		List<Board> list = new ArrayList();
		
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
	    for(Board vo : list)
	    {
	    	LOG.debug("====================================");
			LOG.debug("=vo="+vo);
			LOG.debug("====================================");
	    }
		
		return list;
	}
	
	public Board doSelectOne(int board_seq)
	{
		Board outVO = null;
		Object args[] = {board_seq};
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT                                                 \n");
		sb.append("    div,                                               \n");
		sb.append("    title,                                             \n");
		sb.append("    contents,                                          \n");
		sb.append("    read_cnt,                                          \n");
		sb.append("    TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt,   \n");
		sb.append("    reg_id,                                            \n");
		sb.append("    TO_CHAR(mod_dt,'YYYY-MM-DD HH24MISS') AS mod_dt,   \n");
		sb.append("    mod_id,                                            \n");
		sb.append("    1 AS rnum,                                         \n");
		sb.append("    1 AS total_cnt                                     \n");
		sb.append("FROM board                                             \n");
		sb.append("WHERE board_seq = :v0                                  \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+board_seq);
		LOG.debug("========================");
		
		
		outVO = (Board) this.jdbcTemplate.queryForObject(sb.toString(), 
	                   args, 
	                   rowMapper);
		
		LOG.debug("========================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("========================");
		
		return outVO;
	}
	
	public int doUpdate(Board board)
	{
		int flag = 0;
		Object[]  args = { 
							
						    board.getDiv(),
						    board.getTitle(),
						    board.getContents(),
						    board.getRead_cnt(),
						    board.getModId(),
						    board.getBoard_seq()
						 };
		
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE board          \n");
		sb.append("SET div = ?,          \n");
		sb.append("    title = ?,        \n");
		sb.append("    contents = ?,     \n");
		sb.append("    read_cnt = ?,     \n");
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
	
	public int doDelete(Board board)
	{
		int flag = 0;
		Object[] args = { board.getBoard_seq()};
		
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
	public int doInsert(Board board)
	{
		int flag =0;
		Object[]  args = { 
							board.getBoard_seq(),
						    board.getDiv(),
						    board.getTitle(),
						    board.getContents(),
						    board.getRead_cnt(),
						    board.getRegDt()
						 };
		
		StringBuilder  sb=new StringBuilder();
		sb.append("INSERT INTO board ( \n");
		sb.append("    board_seq,      \n");
		sb.append("    div,            \n");
		sb.append("    title,          \n");
		sb.append("    contents,       \n");
		sb.append("    read_cnt,       \n");
		sb.append("    reg_dt,         \n");
		sb.append("    reg_id,         \n");
		sb.append("    mod_dt,         \n");
		sb.append("    mod_id          \n");
		sb.append(") VALUES (          \n");
		sb.append("    :v0,            \n");
		sb.append("    :v1,            \n");
		sb.append("    :v2,            \n");
		sb.append("    :v3,            \n");
		sb.append("    :v4,            \n");
		sb.append("    :v5,            \n");
		sb.append("    :v6,            \n");
		sb.append("    :v7,            \n");
		sb.append("    :v8             \n");
		sb.append(")                   \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+board);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
}
