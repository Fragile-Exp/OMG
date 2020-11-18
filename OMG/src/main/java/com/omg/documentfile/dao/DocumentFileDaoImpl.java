package com.omg.documentfile.dao;

import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.documentfile.domain.DocumentFileVO;



@Repository("documentFileDao")
public class DocumentFileDaoImpl {
	static final Logger LOG = LoggerFactory.getLogger(DocumentFileDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE ="com.omg.documentfile";
	
	DataSource dataSource; 

	public DocumentFileDaoImpl() {}
	
	public int doInsert(DocumentFileVO documentFileVO) {
		LOG.debug("=doInsert=");
		
		String statement = NAMESPACE+".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardFileVO="+documentFileVO);
		
		int flag = sqlSessionTemplate.insert(statement, documentFileVO);
		return flag;
		
	}
	
	public int doDelete(DocumentFileVO documentFileVO) {
		LOG.debug("=doDelete=");
		
		String statement = NAMESPACE+".doDelete";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardFileVO="+documentFileVO);
		
		int flag = sqlSessionTemplate.delete(statement, documentFileVO);
		return flag;
		
	}
	
	public List<DocumentFileVO> doSelectList(DocumentFileVO documentFileVO) {
		LOG.debug("=doSelectList=");
		String statement = NAMESPACE+".doSelectList";
		LOG.debug("=statement="+statement);
		LOG.debug("=boardFileVO="+documentFileVO);
		
		List<DocumentFileVO> list = this.sqlSessionTemplate.selectList(statement);
		
		return list;
		
	}
	
	
	


}
