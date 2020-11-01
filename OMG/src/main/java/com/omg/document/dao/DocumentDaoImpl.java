package com.omg.document.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.omg.document.domain.DocumentVO;
import com.omg.schedule.dao.ScheduleDaoImpl;
import com.omg.schedule.domain.ScheduleVO;

public class DocumentDaoImpl implements DocumentDao {
	
	final static Logger LOG = LoggerFactory.getLogger(ScheduleDaoImpl.class);
	public DocumentDaoImpl() {}
	@Autowired
    private JdbcTemplate jdbcTemplate;
	DataSource dataSource; 
	
	RowMapper rowMapper = new RowMapper<DocumentVO>() {
		@Override
		public DocumentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			DocumentVO outVO = new DocumentVO();

		    outVO.setDocument_id(rs.getString("document_id"));     //문서고유번호  
		    outVO.setEmployee_id(rs.getString("employee_id"));     //사번    
		    outVO.setKind(rs.getInt("kind"));                      //문서종류  
		    outVO.setTitle(rs.getString("title"));                 //문서제목  
		    outVO.setD_day(rs.getString("d_day"));                 //처리기간  
		    outVO.setDocument_cont(rs.getString("document_cont")); //문서내용  
		    outVO.setDocument_set(rs.getInt("document_set"));      //문서상태  
		    outVO.setOk_user(rs.getString("ok_user"));             //결재자사번 
 		    
		    return outVO;
			}
	    };
	
	//삽입 
	@Override
	public int doInset(DocumentVO documentVO) {
		int flag = 0;
		Object[] args = {
				documentVO.getDocument_id(),
				documentVO.getEmployee_id(),
				documentVO.getKind(),
				documentVO.getTitle(),
				documentVO.getD_day(),
				documentVO.getDocument_cont(),
				documentVO.getDocument_set(),
				documentVO.getOk_user(),
			
		};
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" INSERT INTO document (    \n");
		sb.append("     document_id,          \n");
		sb.append("     employee_id,          \n");
		sb.append("     kind,                 \n");
		sb.append("     title,                \n");
		sb.append("     d_day,                \n");
		sb.append("     document_cont,        \n");
		sb.append("     document_set,         \n");
		sb.append("     ok_user,              \n");
		sb.append(" ) VALUES (                \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?,                    \n");
		sb.append("     ?                     \n");
		sb.append(" )                         \n");
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + documentVO);
		LOG.debug("==============================");
		
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag: " + flag);
		
		return flag;
	}

	@Override
	public int doDelete(DocumentVO documentVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" DELETE FROM document  \n");
		sb.append(" WHERE document_id = ? \n");
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + documentVO);
		LOG.debug("==============================");
		
		Object[] args = { documentVO.getDocument_id() };
		
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag: " + flag);
		
		return flag;
	}

	@Override
	public int doUpdate(DocumentVO documentVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" UPDATE document       \n");
		sb.append(" SET kind = ?          \n");
		sb.append("     title = ?   	  \n");
		sb.append("     d_day = ?         \n");
		sb.append("     document_cont = ? \n");
		sb.append("     OK_USER = ?       \n");
		sb.append(" WHERE document_id = ? \n");
		
		
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + documentVO);
		LOG.debug("==============================");
		
		Object[] args = {
			documentVO.getKind(),
			documentVO.getTitle(),
			documentVO.getD_day(),
			documentVO.getDocument_cont(),
			documentVO.getOk_user(),
			documentVO.getDocument_id()
		};
		
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag: " + flag);	
		
		return flag;
	}

	@Override
	public DocumentVO doSelectOne(DocumentVO documentVO) {
		DocumentVO outVO = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT document_id,   \n");
		sb.append(" 	   employee_id,   \n");
		sb.append(" 	   kind,   		  \n");
		sb.append(" 	   title,   	  \n");
		sb.append(" 	   TO_CHAR(end_dt, 'YYYY-MM-DD') AS d_day,   \n");
		sb.append(" 	   document_cont, \n");
		sb.append(" 	   document_set,  \n");
		sb.append(" 	   ok_user		  \n");
		sb.append(" FROM document         \n");
		sb.append(" WHERE document_id = ? \n");
		
		LOG.debug("==============================");
		LOG.debug("= Parameter: " + documentVO);
		LOG.debug("==============================");
		
		Object[] args = { documentVO.getDocument_id() };
		outVO = (DocumentVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		
		LOG.debug("==============================");
		LOG.debug("= outVO: " + outVO);
		LOG.debug("==============================");
		
		return outVO;
	}

	@Override
	public List<DocumentVO> doSelectList(DocumentVO documentVO) {
		return null;
		
	}

}
