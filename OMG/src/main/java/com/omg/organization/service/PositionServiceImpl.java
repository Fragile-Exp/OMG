package com.omg.organization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.organization.dao.PositionDaoImpl;
import com.omg.organization.domain.PositionVO;

@Service("positionService")
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	PositionDaoImpl dao;
	
	/**
	 * 사원 직급체계 삭제
	 * @param position
	 * @return flag(1:성공)
	 */
	@Override
	public int doDelete(PositionVO position) {
		return dao.doDelete(position);
	}
	
	/**
	 * 사원 직급체계 추가
	 * @param position
	 * @return flag(1:성공)
	 */
	@Override
	public int doInsert(PositionVO position) {
		return dao.doInsert(position);
	}
	
	/**
	 * 사원 직급체계 선택
	 * @param position
	 * @return PositionVO
	 */
	@Override
	public PositionVO doSelectOne(PositionVO position) {
		return dao.doSelectOne(position);
	}
	
	/**
	 * 사원 직급체계 수정
	 * @param position
	 * @return PositionVO
	 */
	@Override
	public int doUpdate(PositionVO position) {
		return dao.doUpdate(position);
	}
	
	/**
	 * 사원 직급체계 조회
	 * @param position
	 * @return PositionVO
	 */
	@Override
	public List<PositionVO> doSelectList() {
		return dao.doSelectList();
	}

}
