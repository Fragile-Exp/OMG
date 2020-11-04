package com.omg.comments.dao;

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

import com.omg.cmn.Search;
import com.omg.comments.domain.CommentsVO;

@Repository("CommentsDao")
public class CommentsDaoImpl implements CommentsDao
{
	final static Logger LOG = LoggerFactory.getLogger(CommentsDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper rowMapper = new RowMapper<CommentsVO>()
	{
		public CommentsVO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			CommentsVO outVO = new CommentsVO();
		
			outVO.setCommentNum(rs.getInt("COMMENT_NUM"));
			outVO.setBoardSeq(rs.getInt("BOARD_SEQ"));
			outVO.setUpNum(rs.getInt("UP_NUM"));
			outVO.setContents(rs.getString("CONTENTS"));
			outVO.setRegDt(rs.getString("REG_DT"));
			
			return outVO;
		}
	};

	public CommentsDaoImpl() {}
	
	@Override
	public List<CommentsVO> doSelectList(Search search) 
	{
		List<CommentsVO> list = new ArrayList();
		
		StringBuilder sbWhere=new StringBuilder();
		//검색구분 != null !"".equals(검색구분)
		if(null != search.getSearchDiv() && !"".equals(search.getSearchDiv()))
		{
			if("10".equals(search.getSearchDiv()))
			{
				sbWhere.append("WHERE contents like '%'|| ? ||'%' \n");
			}
			else if("20".equals(search.getSearchDiv()))
			{
				sbWhere.append("WHERE reg_id like '%'|| ? ||'%' or mod_id like '%'|| ? ||'%' \n");
			}
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT T1.*,T2.*                                                                       \n");
		sb.append("FROM                                                                                   \n");
		sb.append("(                                                                                      \n");
		sb.append("    SELECT B.rnum,                                                                     \n");
		sb.append("           B.comment_num,                                                              \n"); 
		sb.append("           B.board_seq,                                                                \n");     
		sb.append("           B.up_num,                                                                   \n");
		sb.append("           B.contents,                                                                 \n");                                                          
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
		sb.append("            FROM Comments                                                              \n");  
		sb.append("            --WHERE 조건                                                                 \n");
		
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
	    sb.append("    FROM Comments                                                                      \n");  
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
	    for(CommentsVO vo : list)
	    {
	    	LOG.debug("====================================");
			LOG.debug("=vo="+vo);
			LOG.debug("====================================");
	    }
		
		return list;
	}

	

	@Override
	public CommentsVO doSelectOne(CommentsVO Comments) 
	{
		CommentsVO outVO = null;
		Object args[] = {Comments.getCommentNum()};
		
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT                                                  \n");
		sb.append("		comment_num,                                       \n");
		sb.append("		board_seq,                                         \n");
		sb.append("		up_num,                                            \n");
		sb.append("		contents,                                          \n");
		sb.append("		TO_CHAR(reg_dt, 'YYYY-MM-DD HH24MISS') AS reg_dt,  \n");
		sb.append("		reg_id,                                            \n");
		sb.append("		TO_CHAR(reg_dt, 'YYYY-MM-DD HH24MISS') AS reg_dt,  \n");
		sb.append("		mod_id,                                            \n");
		sb.append("		1 AS rnum,                                         \n");
		sb.append("		1 AS total_cnt                                     \n");
		sb.append("		FROM comments                                      \n");
		sb.append("		WHERE comment_num = ?                              \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+Comments);
		LOG.debug("========================");
		
		outVO = (CommentsVO) this.jdbcTemplate.queryForObject(sb.toString(), 
                args, 
                rowMapper);
		
		LOG.debug("========================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("========================");
		
		return outVO;
	}
	
	@Override
	public int doUpdate(CommentsVO Comments) 
	{
		int flag = 0;
		Object[]  args = { 
						    Comments.getBoardSeq(),
						    Comments.getUpNum(),
						    Comments.getContents(),
						    Comments.getRegId(),
						    Comments.getModId()
			 			 };
		
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE comments              \n");
		sb.append("SET 	 board_seq   = ?        \n");
		sb.append("		 up_num 	 = ?        \n");
		sb.append("		 contents 	 = ?        \n");
		sb.append("		 mod_dt 	 = SYSDATE  \n");
		sb.append("		 mod_id 	 = ?        \n");
		sb.append("WHERE comment_num = ?        \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+Comments);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	@Override
	public int doDelete(CommentsVO Comments) 
	{
		int flag = 0;
		
		Object[] args = { Comments.getCommentNum()};

		StringBuilder  sb=new StringBuilder();
		sb.append("DELETE FROM comments   \n");
		sb.append("WHERE comment_num = ?  \n");
		
		LOG.debug("========================");
		LOG.debug("=param=\n"+Comments);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	@Override
	public int doInsert(CommentsVO Comments) 
	{
		int flag =0;
		Object[]  args = { 
							//Comments.getCommentNum(),
						    Comments.getBoardSeq(),
						    Comments.getUpNum(),
						    Comments.getContents(),
						    Comments.getRegId(),
						    Comments.getModId()
			 			 };
		
		StringBuilder  sb=new StringBuilder();
		sb.append("INSERT INTO comments (                      \n");
		sb.append("						comment_num,           \n");
		sb.append("						board_seq,             \n");
		sb.append("						up_num,                \n");
		sb.append("						contents,              \n");
		sb.append("						reg_dt,                \n");
		sb.append("						reg_id,                \n");
		sb.append("						mod_dt,                \n");
		sb.append("						mod_id                 \n");
		sb.append("					  ) VALUES (               \n");
		sb.append("					  	COMMENTS_SEQ.NEXTVAL,  \n");
		sb.append("					  	?,                     \n");
		sb.append("					  	?,                     \n");
		sb.append("					  	?,                     \n");
		sb.append("					  	SYSDATE,               \n");
		sb.append("					  	?,                     \n");
		sb.append("					  	SYSDATE,               \n");
		sb.append("					  	?                      \n");
		sb.append("					  )                        \n");
		LOG.debug("========================");
		LOG.debug("=param=\n"+Comments);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}

	

}
