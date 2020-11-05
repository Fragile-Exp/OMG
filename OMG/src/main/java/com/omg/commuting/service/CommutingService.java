package com.omg.commuting.service;

import java.util.List;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;

public interface CommutingService {
	
	public int doInit();
	
	public int doInsert(DTO dto);
	
	public int doDelete(DTO dto);
	
	public int doUpdateAttendTime(DTO dto);
	
	public int doUpdateLeaveTime(DTO dto);
	
	public DTO doSelectOne(DTO dto); 
	
	public List<Commuting> doSelectList(Search search);
}
