package com.omg.organization.service;

import java.util.List;

import com.omg.organization.domain.PositionVO;

public interface PositionService {

	/**
	 * 사원 직급체계 삭제
	 * @param position
	 * @return flag(1:성공)
	 */
	int doDelete(PositionVO position);

	/**
	 * 사원 직급체계 추가
	 * @param position
	 * @return flag(1:성공)
	 */
	int doInsert(PositionVO position);

	/**
	 * 사원 직급체계 선택
	 * @param position
	 * @return PositionVO
	 */
	PositionVO doSelectOne(PositionVO position);

	/**
	 * 사원 직급체계 수정
	 * @param position
	 * @return PositionVO
	 */
	int doUpdate(PositionVO position);

	/**
	 * 사원 직급체계 조회
	 * @param position
	 * @return PositionVO
	 */
	List<PositionVO> doSelectList();

}