package com.omg.employee;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
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

import com.omg.employee.dao.EmployeeDao;
//메소드 수행 순서:
import com.omg.employee.dao.EmployeeVO;
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
    
	@Before
	public void setUp() throws Exception {
		LOG.debug("++++++++++++++++++++");
		LOG.debug("++context++"+context);
		LOG.debug("++employeeDao++"+employeeDao);
		LOG.debug("++++++++++++++++++++");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
