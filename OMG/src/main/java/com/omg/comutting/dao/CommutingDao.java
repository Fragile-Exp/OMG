package com.omg.comutting.dao;

import java.util.List;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;

public interface CommutingDao {
	
	/**
	 * ë¡œê·¸?¸?‹œ ê¸°ë³¸ row ?ƒ?„±
	 * @param dto
	 * @return
	 * @author ?–‘ê´‘ë??
	 */
	public int doInsert(DTO dto);
	
	/**
	 * ì¶œê·¼,?‡´ê·? ?“±ë¡?
	 * @param dto
	 * @return ?„±ê³?(1), ?‹¤?Œ¨(0)
	 * @author ?–‘ê´‘ë??
	 */
	public int doUpdate(DTO dto);
	
	/**
	 * row ?‚­? œ
	 * @param dto
	 * @return ?„±ê³?(1), ?‹¤?Œ¨(0)
	 * @author ?–‘ê´‘ë??
	 */
	public int doDelete(DTO dto);
	
	/**
	 * ê¸ˆì¼ ë³¸ì¸ ?‹œê°? select
	 * @param dto
	 * @return DTO
	 * @author ?–‘ê´‘ë??
	 */
	public DTO doSelectOne(DTO dto);
	
	/**
	 * ?‚ ì§œë³„ , ë¶??„œë³?
	 * @param search
	 * @return List<Commuting>
	 * ?–‘ê´‘ë??
	 */
	public List<Commuting> doSelectList(Search search);
	
	/**
	 * ³» ±Ù¹«ÇöÈ²
	 * @param search
	 * @return List<Commuting>
	 * ¾ç±¤¹Î
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
	/**
	 * ?‚´ ê·¼ë¬´?˜„?™©
	 * @param search
	 * @return List<Commuting>
	 * ?–‘ê´‘ë??
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
	/**
	 * ?•„ì¹? ê¸°ë³¸ ?‚¬?› insert
	 * @return ?„±ê³?(1), ?‹¤?Œ¨(0)
	 * ?–‘ê´‘ë??
	 */
	public int doInit();
	
	/**
	 * ê·¼ë¬´?‹œê°? update
	 * @param dto
	 * @return ?„±ê³?(1), ?‹¤?Œ¨(0)
	 * ?–‘ê´‘ë??
	 */
	public int doUpdateWorkTime(DTO dto);
	
	
	
}
