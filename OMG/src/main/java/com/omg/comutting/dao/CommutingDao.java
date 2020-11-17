package com.omg.comutting.dao;

import java.util.List;

import com.omg.cmn.Criteria;
import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;

public interface CommutingDao {
	
	/***/
	public int doInsert(DTO dto);
	
	public int doUpdate(DTO dto);
	
	public int doDelete(DTO dto);
	
	public DTO doSelectOne(DTO dto);
	
	public List<Commuting> doSelectList(Criteria criteria);
	
	public List<Commuting> doSelectMyList(DTO dto);
	
	public int doInit();
	
	public int doUpdateWorkTime(DTO dto);
	
	public List<Commuting> getAll();
	
	public int getTotalCount(Criteria criteria);
	
}
