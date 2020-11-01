package com.omg.comutting.dao;

import java.util.List;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;

public interface CommutingDao {
	
	
	public int doDelete(DTO dto);
	
	
	public DTO doSelectToday(DTO dto);
	
	public List<Commuting> doSelectList(Search search);
	
	public int doUpsert(DTO dto);
}
