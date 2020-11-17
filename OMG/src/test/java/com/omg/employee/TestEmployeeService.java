package com.omg.employee;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.Arrays;
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

import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.employee.dao.EmployeeDao;
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeService;
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestEmployeeService {
	final static Logger   LOG = LoggerFactory.getLogger(TestEmployeeService.class);
	
    @Autowired //테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
    WebApplicationContext  context ;
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    EmployeeService employeeService;
    
    EmployeeVO employee01;
    EmployeeVO employee02;
    EmployeeVO employee03;
    
    
    
	@Before
	public void setUp() throws Exception {
		LOG.debug("=========================");
		LOG.debug("=setUp()=");
		//로그인 성공
		employee01=new EmployeeVO("ID01","123456","유비_01",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");
		//없는 아이디
		employee02=new EmployeeVO("noId","123456","유비_02",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");
		//틀린 비밀번호
		employee03=new EmployeeVO("ID03","123456_wrong","유비_03",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");
		LOG.debug("=employee01()="+employee01);
		LOG.debug("=========================");
		
	}
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("=========================");
		LOG.debug("=tearDown()=");		
		LOG.debug("=========================");
	}
	@Test
	@Ignore
	public void idCheck() {
		LOG.debug("=========================");
		LOG.debug("=idCheck()=");		
		LOG.debug("=========================");
		employeeService.idCheck(employee01);
	}
	@Test
	public void doLogin() {
		LOG.debug("=========================");
		LOG.debug("=doLogin()=");		
		LOG.debug("=========================");
		employeeService.doLogin(employee03);
		
	}
	
	@Test
	public void bean() {
		LOG.debug("=========================");
		LOG.debug("=bean()=");
		LOG.debug("=context:"+context);
		LOG.debug("=employeeDao:"+employeeDao);	
		LOG.debug("=employeeService:"+employeeService);	
		LOG.debug("=========================");
		
		assertThat(context, is(notNullValue()));
		assertThat(employeeDao, is(notNullValue()));
	}

}
