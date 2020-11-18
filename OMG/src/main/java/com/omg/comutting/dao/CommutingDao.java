package com.omg.comutting.dao;

import java.util.List;

import com.omg.cmn.Criteria;
import com.omg.cmn.DTO;
import com.omg.commuting.domain.Commuting;

public interface CommutingDao {
	
	/**
	 * 한건 입력
	 * @param dto
	 * @return
	 */
	public int doInsert(DTO dto);
	
	/**
	 * 출근시간, 퇴근시간 Update
	 * @param dto
	 * @return
	 */
	public int doUpdate(DTO dto);
	
	/**
	 * 관리자 기능 : 한 건 지우기
	 * @param dto
	 * @return
	 */
	public int doDelete(DTO dto);
	
	/**
	 * 오늘 내 근무 현황 가져오기
	 * @param dto
	 * @return
	 */
	public DTO doSelectOne(DTO dto);
	
	/**
	 * 금일 부서별 출근 리스트 가져오기
	 * @param criteria
	 * @return
	 */
	public List<Commuting> doSelectList(Criteria criteria);
	
	/**
	 * 내 근무 이력 
	 * @param dto
	 * @return
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
	/**
	 * 관리자 기능 : 근무상태 초기화
	 * @return
	 */
	public int doInit();
	
	/**
	 * 근무 시간  update
	 * @param dto
	 * @return
	 */
	public int doUpdateWorkTime(DTO dto);
	
	/**
	 * 모든 row 가져오기
	 * @return
	 */
	public List<Commuting> getAll();
	
	/**
	 * dept 조건에 맞춰 총 인원 세기
	 * @param criteria
	 * @return
	 */
	public int getTotalCount(Criteria criteria);
	
}
