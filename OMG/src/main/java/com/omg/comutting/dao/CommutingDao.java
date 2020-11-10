package com.omg.comutting.dao;

import java.util.List;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;

public interface CommutingDao {
	
	/**
	 * 로그인시 기본 row 생성
	 * @param dto
	 * @return
	 * @author 양광민
	 */
	public int doInsert(DTO dto);
	
	/**
	 * 출근,퇴근 등록
	 * @param dto
	 * @return 성공(1), 실패(0)
	 * @author 양광민
	 */
	public int doUpdate(DTO dto);
	
	/**
	 * row 삭제
	 * @param dto
	 * @return 성공(1), 실패(0)
	 * @author 양광민
	 */
	public int doDelete(DTO dto);
	
	/**
	 * 금일 본인 시간 select
	 * @param dto
	 * @return DTO
	 * @author 양광민
	 */
	public DTO doSelectOne(DTO dto);
	
	/**
	 * 날짜별 , 부서별
	 * @param search
	 * @return List<Commuting>
	 * 양광민
	 */
	public List<Commuting> doSelectList(Search search);
	
	/**
	 * 내 근무현황
	 * @param search
	 * @return List<Commuting>
	 * 양광민
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
	/**
	 * 아침 기본 사원 insert
	 * @return 성공(1), 실패(0)
	 * 양광민
	 */
	public int doInit();
	
	/**
	 * 근무시간 update
	 * @param dto
	 * @return 성공(1), 실패(0)
	 * 양광민
	 */
	public int doUpdateWorkTime(DTO dto);
	
	
	
}
