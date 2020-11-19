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
import com.omg.employee.domain.EmployeeVO;

@Repository("documentDao")
public class DocumentDaoImpl {
	
	static final Logger LOG = LoggerFactory.getLogger(DocumentDaoImpl.class);
	
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE ="com.omg.document";
	
	
	
	
	
	DataSource dataSource; 
	
	RowMapper rowMapper = new RowMapper<DocumentVO>() {
		@Override
		public DocumentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			DocumentVO outVO = new DocumentVO();

		    outVO.setDocumentId(rs.getString("documentId"));     //문서고유번호  
		    outVO.setEmployeeId(rs.getString("employeeId"));     //사원아이디    
		    outVO.setKind(rs.getInt("kind"));                      //문서종류  
		    outVO.setTitle(rs.getString("title"));                 //문서
		    outVO.setdDay(rs.getString("dDay"));                 //처리기간  
		    outVO.setDocumentCont(rs.getString("documentCont")); //문서내용  
		    outVO.setDocumentSet(rs.getInt("documentSet"));      //문서상태  
		    outVO.setOkUser(rs.getString("okUser"));             //승인자 
 		    
		    return outVO;
		}
	};
	
	public DocumentDaoImpl() {}
	   
	    

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
	
	
	public int doempIdcheck(DocumentVO documentVO) {
		LOG.debug("=doempIdcheck=");
		
		String statement = NAMESPACE+".doempIdcheck";
		
		int flag = sqlSessionTemplate.selectOne(statement, documentVO);
		return flag;
		
	}
	
	
	public int doSeleteAllCount(DocumentVO documentVO) {
		LOG.debug("=doSeleteAllCount=");
		
		String statement = NAMESPACE+".doSeleteAllCount";
		
		int flag = sqlSessionTemplate.selectOne(statement, documentVO);
		return flag;
		
	}
	
	public int doovercheck(DocumentVO documentVO) {
		LOG.debug("=doovercheck=");
		
		String statement = NAMESPACE+".doovercheck";
		
		int flag = sqlSessionTemplate.selectOne(statement, documentVO);
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
		LOG.debug("=list="+list);
		return list;
	} 
	
	

	public List<DocumentVO> doSeleteListManager(DocumentVO documentVO){
		LOG.debug("=doSeleteListManager=");
		String statement = NAMESPACE+".doSeleteListManager";
		LOG.debug("=statement="+statement);
		LOG.debug("=serach="+documentVO);
		List<DocumentVO> list = this.sqlSessionTemplate.selectList(statement, documentVO);
		LOG.debug("=list="+list);
		return list;
	} 
	

	public int domanagerIdcheck(DocumentVO documentVO) {
		LOG.debug("=domanagerIdcheck=");
		
		String statement = NAMESPACE+".domanagerIdcheck";
		
		int flag = sqlSessionTemplate.selectOne(statement, documentVO);
		return flag;
		
	}
	
	
	public String doempName(EmployeeVO employee) {
		LOG.debug("=doempName=");
		
		String statement = NAMESPACE+".doempName";
		
		LOG.debug("=statement ="+statement );
		LOG.debug("=serach="+employee);
		
		String Id= this.sqlSessionTemplate.selectOne(statement, employee);
		
		return Id;
	}

	public EmployeeVO doempIdSelete(EmployeeVO employee) {
		String statement = NAMESPACE+".doempIdSelete";
		LOG.debug("=statement ="+statement );
		LOG.debug("=serach="+employee);
		EmployeeVO Id= this.sqlSessionTemplate.selectOne(statement, employee);
		LOG.debug("doempIdSelete: " + Id);
		return Id;

	}
	
	public int doMaxNumberId(DocumentVO documentVO) {
		LOG.debug("=doMaxNumberId=");
		
		String statement = NAMESPACE+".doMaxNumberId";
		
		int flag = sqlSessionTemplate.selectOne(statement, documentVO);
		return flag;
		
	}
	
	public List<EmployeeVO> doempNameget(EmployeeVO employee) {
		LOG.debug("=doempNameget=");
		
		String statement = NAMESPACE+".doempNameget";
		
		 List<EmployeeVO>  list = sqlSessionTemplate.selectList(statement, employee);
		
		
		return list;
		
	}
	
	public int dodocumentenroll(DocumentVO documentVO) {
		LOG.debug("=dodocumentenroll=");
		
		String statement = NAMESPACE+".dodocumentenroll";
		
		int flag = sqlSessionTemplate.selectOne(statement, documentVO);
		return flag;
	}
	
	
	public int dodocumentapp(DocumentVO documentVO) {
		LOG.debug("=dodocumentapp=");
		
		String statement = NAMESPACE+".dodocumentapp";
		
		int flag = sqlSessionTemplate.selectOne(statement, documentVO);
		return flag;
	}
	
	public int dosetUpdate(DocumentVO documentVO) {
		LOG.debug("=dosetUpdate=");
		
		String statement = NAMESPACE+".dosetUpdate";
		
		int flag = sqlSessionTemplate.update(statement, documentVO);
		return flag;
		
	}
	
}
