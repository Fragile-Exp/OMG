package com.omg.employee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.omg.cmn.Search;
import com.omg.employee.dao.EmployeeDao;
import com.omg.employee.domain.EmployeeVO;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestEmployee {
	final static Logger   LOG = LoggerFactory.getLogger(TestEmployee.class);
	
    @Autowired //테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
    WebApplicationContext  context ;
    
    @Autowired
    EmployeeDao employeeDao;
    
    EmployeeVO employee01;
    EmployeeVO employee02;
    EmployeeVO employee03;  
    EmployeeVO employee04;
    EmployeeVO employee05;
    
    @Test
    @Ignore
    public void doSelectList() {
    	
    	//조회
    	Search search=new Search("20","1",3,1);
    	List<EmployeeVO> list=employeeDao.doSelectList(search);
    	LOG.debug("list.size()="+list.size());
    	
    }
    
    @Test
    @Ignore
    public void passwdConfirm() throws ClassNotFoundException   {
    	LOG.debug("++++++++++++++++++++");
		LOG.debug("++idConfirm()++");
		LOG.debug("++++++++++++++++++++");
    	int cnt=employeeDao.passwdConfirm(employee05);
    	
    	LOG.debug("=비밀번호 확인="+cnt);
    }
    
    @Test
    @Ignore
    public void idConfirm() throws ClassNotFoundException {
    	LOG.debug("++++++++++++++++++++");
		LOG.debug("++idConfirm()++");
		LOG.debug("++++++++++++++++++++");
    	int cnt=employeeDao.idConfirm(employee01);
    	
    	
    	LOG.debug("=flag="+cnt);
    }
    
    @Test
    @Ignore
    public void getAll() {
    	//기존 데이터 삭제
    	employeeDao.doDelete(employee01);
    	employeeDao.doDelete(employee02);
    	employeeDao.doDelete(employee03);
    	
    	//데이터 입력
    	employeeDao.doInsert(employee01);
    	employeeDao.doInsert(employee02);
    	employeeDao.doInsert(employee03);
    	
    	//전체 데이터 조회: 3건 확인
    	EmployeeVO cntEmployee=new EmployeeVO();
    	cntEmployee.setEmployee_id("ID");
    	
    	List<EmployeeVO> list=employeeDao.doSelectAll(cntEmployee);
    	assertThat(list.size(), is(3));
    	
    	//입력데이터와 비교
    	checkUser(employee01, list.get(0));
    	checkUser(employee02, list.get(1));
    	checkUser(employee03, list.get(2));
    }
    
    @Test 
    @Ignore
    public void doUpdate() {
    	//기존 데이터 삭제
    	employeeDao.doDelete(employee01);
    	
    	//단건입력
    	int flag=employeeDao.doInsert(employee01);
    	assertThat(flag, is(1));
    	
    	//수정
    	employee01.setPassword(employee01.getPassword()+"_U");
    	employee01.setDept_no(employee01.getDept_no()+000);
    	employee01.setPosition_no(employee01.getPosition_no()+000);
    	employee01.setCell_phone(employee01.getCell_phone()+000);
    	employee01.setEmail(employee01.getEmail()+"_U");
    	employee01.setAddress(employee01.getAddress()+"_U");
    	employee01.setHoliday(employee01.getHoliday()+000);
    	employee01.setImg_code(employee01.getImg_code()+000);
    	
    	flag=employeeDao.doUpdate(employee01);
    	assertThat(1, is(1));
    	
    	//단건조회
    	EmployeeVO vsEmployee=employeeDao.doSelectOne(employee01.getEmployee_id());
    	
    	//수정과 단건조회 비교
    	checkUser(employee01, vsEmployee);
    	
    }
    
    @Test
    public void addAndGet() {
    	//기존 데이터 삭제
    	employeeDao.doDelete(employee01);
    	employeeDao.doDelete(employee02);
    	employeeDao.doDelete(employee03);
    	
    	//추가
    	int flag=employeeDao.doInsert(employee01);
    	assertThat(flag, is(1));
    	
    	EmployeeVO cntEmployee=new EmployeeVO();
    	cntEmployee.setEmployee_id("ID");
    	
    	//등록건수조회
    	int cnt=employeeDao.count(cntEmployee);
    	assertThat(cnt, is(1));
    	
    	flag=employeeDao.doInsert(employee02);
    	assertThat(flag, is(1));
    	
    	cnt=employeeDao.count(cntEmployee);
    	assertThat(cnt, is(2));
    	
    	flag=employeeDao.doInsert(employee03);
    	assertThat(flag, is(1));
    	
    	cnt=employeeDao.count(cntEmployee);
    	assertThat(cnt, is(3));
    	
    	//단건조회
    	EmployeeVO vsUser01=employeeDao.doSelectOne(employee01.getEmployee_id());
    	EmployeeVO vsUser02=employeeDao.doSelectOne(employee02.getEmployee_id());
    	EmployeeVO vsUser03=employeeDao.doSelectOne(employee03.getEmployee_id());
    	//조회 데이터 검증
    	checkUser(employee01, vsUser01);
    	checkUser(employee02, vsUser02);
    	checkUser(employee03, vsUser03);
    	
    	
    }
    
    private void checkUser(EmployeeVO orgEmployee, EmployeeVO vsEmployee) {
    	assertThat(orgEmployee.getEmployee_id(),is(vsEmployee.getEmployee_id()));
    	assertThat(orgEmployee.getPassword(),is(vsEmployee.getPassword()));
    	assertThat(orgEmployee.getName(), is(vsEmployee.getName()));
    	assertThat(orgEmployee.getDept_no(), is(vsEmployee.getDept_no()));
    	assertThat(orgEmployee.getPosition_no(), is(vsEmployee.getPosition_no()));
    	assertThat(orgEmployee.getCell_phone(), is(vsEmployee.getCell_phone()));
    	assertThat(orgEmployee.getEmail(),is(vsEmployee.getEmail()));
    	assertThat(orgEmployee.getAddress(), is(vsEmployee.getAddress()));
    	assertThat(orgEmployee.getHoliday(), is(vsEmployee.getHoliday()));
    	assertThat(orgEmployee.getImg_code(), is(vsEmployee.getImg_code()));
    	
    }
    
    
	@Before
	public void setUp() throws Exception {
		LOG.debug("++++++++++++++++++++");
		LOG.debug("++context++"+context);
		LOG.debug("++employeeDao++"+employeeDao);
		LOG.debug("++++++++++++++++++++");
		//"아이디","비미번호","이름","부서번호","직급번호","휴대폰","이메일","주소","입사일","생일","휴가일","이미지코드"
		employee01=new EmployeeVO("ID01","123456","유비_01",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1");
		employee02=new EmployeeVO("ID02","123456","유비_02",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1");
		employee03=new EmployeeVO("ID03","123456","유비_03",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1");
		//없는 아이디
		employee04=new EmployeeVO("noId","123456","유비_02",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1");
		//틀린 비밀번호
		employee05=new EmployeeVO("ID03","123456_wrong","유비_03",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1");
				
	}
	
	@After
	public void tearDown() throws Exception {
	}


}
