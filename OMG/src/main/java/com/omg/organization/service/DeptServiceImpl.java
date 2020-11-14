package com.omg.organization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.organization.dao.DeptDaoImpl;
import com.omg.organization.dao.PositionDaoImpl;
import com.omg.organization.domain.DeptVO;
import com.omg.organization.domain.PositionVO;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	DeptDaoImpl dao;
	
	/**
	 * 사원 직급체계 삭제
	 * @param position
	 * @return flag(1:성공)
	 */
	@Override
	public int doDelete(DeptVO dept) {
		return dao.doDelete(dept);
	}
	
	/**
	 * 사원 직급체계 추가
	 * @param position
	 * @return flag(1:성공)
	 */
	@Override
	public int doInsert(DeptVO dept) {
		return dao.doInsert(dept);
	}
	
	/**
	 * 사원 직급체계 선택
	 * @param position
	 * @return PositionVO
	 */
	@Override
	public DeptVO doSelectOne(DeptVO dept) {
		return dao.doSelectOne(dept);
	}
	
	/**
	 * 사원 직급체계 수정
	 * @param position
	 * @return PositionVO
	 */
	@Override
	public int doUpdate(DeptVO dept) {
		return dao.doUpdate(dept);
	}
	
	/**
	 * 사원 직급체계 조회
	 * @param position
	 * @return PositionVO
	 */
	@Override
	public List<DeptVO> doSelectList() {
		return dao.doSelectList();
	}

}
