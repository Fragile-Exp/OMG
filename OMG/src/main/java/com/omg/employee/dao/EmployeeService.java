package com.omg.employee.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.cmn.Message;
import com.omg.cmn.Search;

@Service("employeeService")
public class EmployeeService{
	final Logger LOG=LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public EmployeeService() {}
	
	/**
	 * 로그인시 아이디 존재 여부
	 * @param employee
	 * @return 1(존재하지 않는 아이디),0(존재하는 아이디) 
	 */
	public int idCheck(EmployeeVO employee) {
		int flag=0;
		int idFlag=employeeDao.idConfirm(employee);
		if(idFlag==1) {
			LOG.debug("이미 존재하는 아이디입니다.");
		}else {
			LOG.debug("존재하지 않는 아이디입니다.");
			flag=1;
		}
		return flag;
	}
	/**
	 * 로그인
	 * @param employee
	 * @return
	 */
	public int doLogin(EmployeeVO employee) {
		int flag=0;

		int idFlag=employeeDao.idConfirm(employee);
		
		
		if(idFlag!=1) { //아이디 존재 여부
			LOG.debug("아이디가 없습니다.");
			return flag;
		}else {
			int passwdFlag=employeeDao.passwdConfirm(employee);
			if(passwdFlag!=1) {//비밀번호 존재 여부
				LOG.debug("비밀번호가 맞지 않습니다.");
			}else {
				LOG.debug("로그인에 성공하셨습니다.");
				flag=1;
			}
			
		}
		
		return flag;
	}
	
}
