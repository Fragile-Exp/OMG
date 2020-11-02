package com.omg.document.dao;

import java.util.List;

import com.omg.document.domain.DocumentVO;
import com.omg.schedule.domain.ScheduleVO;

public interface DocumentDao {

	public int doInset(DocumentVO documentVO) ;
	public int doDelete(DocumentVO documentVO) ;
	public int doUpdate(DocumentVO documentVO) ;
	public DocumentVO doSelectOne(DocumentVO documentVO);
	public List<DocumentVO> doSelectList(DocumentVO documentVO);

	
}
