package com.omg.attachment.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.attachment.domain.AttachmentVO;

@Repository
public class AttachmentDaoImpl 
{
	final static Logger LOG = LoggerFactory.getLogger(AttachmentDaoImpl.class);
	
	/** NAMESPACE */
	private final String NAMESPACE = "com.omg.attachment";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public AttachmentDaoImpl() {}
	
	public List<AttachmentVO> doSelectList(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectList=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectList";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		List<AttachmentVO> list = this.sqlSessionTemplate.selectList(statement, attachment);
		
		for(AttachmentVO vo : list)
		{
			LOG.debug("vo : "+vo);
		}
		
		return list;
	}
	
	public AttachmentVO doSelectOne(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectOne";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		AttachmentVO outVO = this.sqlSessionTemplate.selectOne(statement, attachment);
		
		return outVO;
	}
	
	public int doDeleteOne(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doDeleteOne=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doDeleteOne";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.delete(statement, attachment);
		LOG.debug("=flag : "+flag);
		
		return flag;
	}
	
	public int doDelete(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doDelete=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doDelete";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.delete(statement, attachment);
		LOG.debug("=flag : "+flag);
		
		return flag;
	}
	
	public int doInsert(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doInsert=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doInsert";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.insert(statement, attachment);
		
		return flag;
	}
	
	
	
}
