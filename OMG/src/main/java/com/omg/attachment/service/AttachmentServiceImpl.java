package com.omg.attachment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.attachment.dao.AttachmentDaoImpl;
import com.omg.attachment.domain.AttachmentVO;

@Service("AttachmentService")
public class AttachmentServiceImpl
{
	final Logger LOG = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Autowired
	private AttachmentDaoImpl  attachmentDao;
	
	public AttachmentServiceImpl() {}
	
	public List<AttachmentVO> doSelectList(AttachmentVO attachment)
	{
		return attachmentDao.doSelectList(attachment);
	}
	
	public AttachmentVO doSelectOne(AttachmentVO attachment)
	{
		return attachmentDao.doSelectOne(attachment);
	}
	
	public int doDeleteOne(AttachmentVO attachment)
	{
		return attachmentDao.doDeleteOne(attachment);
	}
	
	public int doDelete(AttachmentVO attachment)
	{
		return attachmentDao.doDelete(attachment);
	}
	
	public int doInsert(AttachmentVO attachment)
	{
		
		return attachmentDao.doInsert(attachment);
	}
	
	public int doUpdate(AttachmentVO attachment)
	{
		attachmentDao.doDelete(attachment);
		return attachmentDao.doInsert(attachment);
	}
	
}
