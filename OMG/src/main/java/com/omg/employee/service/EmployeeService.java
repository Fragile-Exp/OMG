package com.omg.employee.service;

import java.util.List;

import com.omg.cmn.Search;
import com.omg.employee.domain.EmployeeVO;

public interface EmployeeService {

	/**
	 * 비밀번호 확인
	 * @param employee
	 * @return 1(비밀번호 일치)/0(비밀번호 불일치)
	 */
	int passwdConfirm(EmployeeVO employee);

	/**
	 * 아이디 존재여부
	 * @param employee
	 * @return 1(존재)/0(존재하지 않는 아이디)
	 */
	int idConfirm(EmployeeVO employee);

	List<EmployeeVO> doSelectList(Search search);

	List<EmployeeVO> doSelectAll(EmployeeVO employee);

	int doUpdate(EmployeeVO employee);

	EmployeeVO doSelectOne(EmployeeVO employee);

	/**
	 * 전체삭제
	 * @return
	 */
	int doDeleteAll();

	/**
	 * 삭제
	 * @param employee
	 * @return
	 */

	int doDelete(EmployeeVO employee);

	/**
	 * 등록
	 * @param employee
	 * @return
	 */
	int doInsert(EmployeeVO employee);

	/**
	 * 로그인시 아이디 존재 여부
	 * @param employee
	 * @return 1(존재하지 않는 아이디),0(존재하는 아이디) 
	 */
	int idCheck(EmployeeVO employee);

	/**
	 * 로그인
	 * @param employee
	 * @return
	 */
	int doLogin(EmployeeVO employee);

}