package com.omg.employee;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//메소드 수행 순서:

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeServiceImpl;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                               "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestEmployeeController {
	final Logger LOG = LoggerFactory.getLogger(TestEmployeeController.class);
	
	@Autowired
	WebApplicationContext  webApplicationContext;
	
	List<EmployeeVO> employees;
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	//브라우저 대신 Mock
	MockMvc  mockMvc;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=========================");
		LOG.debug("=setUp()=");
		employees=Arrays.asList(
				new EmployeeVO("ID01","123456","유비_01",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1"),
				new EmployeeVO("ID02","123456","유비_02",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1"),
				new EmployeeVO("ID03","123456","유비_03",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1"),
				new EmployeeVO("ID04","123456","유비_04",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1"),
				new EmployeeVO("ID05","123456","유비_05",1,1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1")
				);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=mockMvc="+mockMvc);
		assertThat(mockMvc, is(notNullValue()));
		LOG.debug("=========================");
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	

	public int doInsert(EmployeeVO employee) throws Exception {
		MockHttpServletRequestBuilder  createMessage
		 			=MockMvcRequestBuilders.post("/employee/doInsert.do")	
		 			.param("employee_id", employee.getEmployee_id())
		 			.param("password", employee.getPassword())
		 			.param("name", employee.getName())
		 			.param("dept_no", employee.getDept_no()+"")
		 			.param("position_no", employee.getPosition_no()+"")
		 			.param("cell_phone", employee.getCell_phone()+"")
		 			.param("email", employee.getEmail())
		 			.param("address", employee.getAddress())
		 			.param("hire_date", employee.getHire_date())
		 			.param("birth_day", employee.getBirth_day())
		 			.param("holiday", employee.getHoliday()+"")
		 			.param("img_code", employee.getImg_code())
		 			;
		ResultActions resultActions = mockMvc.perform(createMessage)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.msgId", is("1")))
                ;	
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result="+result);
		LOG.debug("===========================");	
		//json -> Message
		Gson gson=new Gson();
		Message outVO=gson.fromJson(result, Message.class);
		LOG.debug("===========================");
		LOG.debug("=Message="+outVO);
		LOG.debug("===========================");	
	
		return Integer.valueOf(outVO.getMsgId());
	}
	@Test
	public void beans() {
		LOG.debug("---------------------------");
		LOG.debug("webApplicationContext:"+webApplicationContext);
		LOG.debug("employeeService:"+employeeService);
		LOG.debug("---------------------------");
		
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(employeeService, is(notNullValue()));
	}

}
