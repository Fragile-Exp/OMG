package com.omg.commuting.service;

import java.util.List;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;

public interface CommutingService {
	
	/**
	 * 배치 돌리기
	 * @return
	 */
	public int doInit();
	
	/**
	 * 아침데이터 수동 주입
	 * @param dto
	 * @return
	 */
	public int doInsert(DTO dto);
	
	/**
	 * 관리자가 사원 근무기록 삭제하기
	 * @param dto
	 * @return
	 */
	public int doDelete(DTO dto);
	
	/**
	 * 출근 시간 , 상태 지정
	 * @param dto
	 * @return
	 */
	public int doUpdateAttendTime(DTO dto);
	
	/**
	 * 퇴근 시간, 상태 지정
	 * @param dto
	 * @return
	 */
	public int doUpdateLeaveTime(DTO dto);
	
	/**
	 * 근무 시간 확인
	 * @param dto
	 * @return
	 */ 
	public DTO doSelectOne(DTO dto); 
	
	/**
	 * 검색조건에 따라 다른 서비스 호출
	 * @param search
	 * @return
	 */
	public List<Commuting> doSelectList(Search search);
	
	/**
	 * 내 근무 환형
	 * @param dto
	 * @return
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
}
