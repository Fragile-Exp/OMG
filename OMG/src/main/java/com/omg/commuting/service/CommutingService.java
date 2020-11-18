package com.omg.commuting.service;

import java.util.List;

import com.omg.cmn.Criteria;
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
	 * 금일 현재 내 근무
	 * @param dto
	 * @return
	 */ 
	public DTO get(DTO dto); 
	
	/**
	 * 검색조건에 따라 다른 서비스 호출
	 * 10(id) , 20(dept), 30(month) , 40(seq)
	 * @param search
	 * @return
	 */
	public List<Commuting> doSelectList(Criteria criteria);
	
	/**
	 * 내 근무 환형
	 * @param dto
	 * @return
	 */
	public List<Commuting> doSelectMyList(DTO dto);
	
	/**
	 * 전체 목록
	 */
	public List<Commuting> getAll();
	
	/**
	 * 부서 총 인원 불러오기
	 * @param criteria
	 * @return
	 */
	public int getTotalCount(Criteria criteria);
}
