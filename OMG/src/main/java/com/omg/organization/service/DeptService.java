package com.omg.organization.service;

import java.util.List;

import com.omg.organization.domain.DeptVO;

public interface DeptService {

	/**
	 * 사원 직급체계 삭제
	 * @param position
	 * @return flag(1:성공)
	 */
	int doDelete(DeptVO dept);

	/**
	 * 사원 직급체계 추가
	 * @param position
	 * @return flag(1:성공)
	 */
	int doInsert(DeptVO dept);

	/**
	 * 사원 직급체계 선택
	 * @param position
	 * @return PositionVO
	 */
	DeptVO doSelectOne(DeptVO dept);

	/**
	 * 사원 직급체계 수정
	 * @param position
	 * @return PositionVO
	 */
	int doUpdate(DeptVO dept);

	/**
	 * 사원 직급체계 조회
	 * @param position
	 * @return PositionVO
	 */
	List<DeptVO> doSelectList();

}