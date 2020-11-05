package com.omg.document.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.document.domain.DocumentVO;





@Repository("documentDao")
public class DocumentDaoImpl {
	
	static final Logger LOG = LoggerFactory.getLogger(DocumentDaoImpl.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	private final String NAMESPACE ="com.omg.document";
	
	DataSource dataSource; 
	
	RowMapper rowMapper = new RowMapper<DocumentVO>() {
		@Override
		public DocumentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			DocumentVO outVO = new DocumentVO();

		    outVO.setDocumentId(rs.getString("documentId"));     //문서고유번호  
		    outVO.setEmployeeId(rs.getString("employeeId"));     //사번    
		    outVO.setKind(rs.getInt("kind"));                      //문서종류  
		    outVO.setTitle(rs.getString("title"));                 //문서제목  
		    outVO.setdDay(rs.getString("dDay"));                 //처리기간  
		    outVO.setDocumentCont(rs.getString("documentCont")); //문서내용  
		    outVO.setDocumentSet(rs.getInt("documentSet"));      //문서상태  
		    outVO.setOkUser(rs.getString("okUser"));             //결재자사번 
 		    
		    return outVO;
		}
	};
	
	public DocumentDaoImpl() {}
	   
	    
	//삽입 
	public int doInsert(DocumentVO documentVO) {
		LOG.debug("=doInsert=");
		
		String statement = NAMESPACE+".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+documentVO);
		
		int flag = sqlSessionTemplate.insert(statement, documentVO);
		return flag;
		
	}

	
	public int doDelete(DocumentVO documentVO) {
		LOG.debug("=doDelete=");
		
		String statement = NAMESPACE+".doDelete";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+documentVO);
		
		int flag = sqlSessionTemplate.delete(statement, documentVO);
		return flag;
		
	}


	public int doUpdate(DocumentVO documentVO) {
		LOG.debug("=doUpdate=");
		
		String statement = NAMESPACE+".doUpdate";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+documentVO);
		
		int flag = sqlSessionTemplate.update(statement, documentVO);
		return flag;
		
	}


	public DocumentVO doSelectOne(DocumentVO documentVO) {
		LOG.debug("=doSelectOne=");
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardVO="+documentVO);
		
		DocumentVO outVO = this.sqlSessionTemplate.selectOne(statement, documentVO);
		LOG.debug("=outVO="+outVO);
		
		return outVO;
	}

	
	public List<DocumentVO> doSelectList() {
		LOG.debug("=doSelectList=");
		String statement = NAMESPACE+".doSelectList";
		LOG.debug("=statement="+statement);
		
		List<DocumentVO> list = this.sqlSessionTemplate.selectList(statement);
		
		return list;
		
	}
	
	public List<DocumentVO> doempIdSelectList(DocumentVO documentVO){
		LOG.debug("=doempIdSelectList=");
		String statement = NAMESPACE+".doempIdSelectList";
		LOG.debug("=statement="+statement);
		LOG.debug("=serach="+documentVO);
		List<DocumentVO> list = this.sqlSessionTemplate.selectList(statement, documentVO);
		
		return list;
	} 
	

}
