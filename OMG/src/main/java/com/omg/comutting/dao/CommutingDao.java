package com.omg.comutting.dao;

import java.util.List;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;

public interface CommutingDao {
	
	public int doInsert(DTO dto);
	
	public int doDelete(DTO dto);
	
	public int doUpdate(DTO dto);
	
	public DTO doSelectOne(DTO dto);
	
	public List<Commuting> doSelectList(Search search);
}
