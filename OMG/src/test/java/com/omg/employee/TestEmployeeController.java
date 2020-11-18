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
import org.junit.Ignore;
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


import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeService;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                               "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestEmployeeController {
	final Logger LOG = LoggerFactory.getLogger(TestEmployeeController.class);
	
	@Autowired
	WebApplicationContext  webApplicationContext;
	
	List<EmployeeVO> employees;
	
	@Autowired
	EmployeeService employeeService;
	
	MockMvc  mockMvc;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=========================");
		LOG.debug("=setUp()=");
		employees=Arrays.asList(
				new EmployeeVO("ID01","123456","유비_01",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1"),
				new EmployeeVO("ID02","123456","유비_02",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1"),
				new EmployeeVO("ID03","123456","유비_03",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1"),
				new EmployeeVO("noId","123456","유비_02",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1"),
				new EmployeeVO("ID03","123456_wrong","유비_03",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1")
				);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=mockMvc="+mockMvc);
		assertThat(mockMvc, is(notNullValue()));
		LOG.debug("=========================");
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void idConfirm() {
		LOG.debug("++++++++++++++++++++");
		LOG.debug("++idConfirm()++");
		LOG.debug("++++++++++++++++++++");
		
		EmployeeVO employee01=employees.get(4);
    	int cnt=employeeService.idConfirm(employee01);
    	
    	//1(존재)/0(존재하지 않음)
    	LOG.debug("=cnt="+cnt);
		
	}
	
	@Test
	@Ignore
	public void doSelectList() throws Exception {
		Search search=new Search("","",10,1);
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/employee/doSelectList.do")
				.param("searchDiv", search.getSearchDiv())
				.param("searchWord", search.getSearchWord())
				.param("pageSize", search.getPageSize()+"")
				.param("pageNum", search.getPageNum()+"");
		
		ResultActions resultActions = this.mockMvc.perform(createMessage)
	            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
	            .andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("=================" + result);
		LOG.debug("=result=" + result);
		LOG.debug("=================" + result);
		
		
	}
	
	@Test
	@Ignore
	public void doUpdate() throws Exception {
		//삭제
		for(EmployeeVO vo:employees) {
			doDelete(vo);
		}
		
		//단건입력
		int flag=doInsert(employees.get(0));
		assertThat(flag, is(1));
		
		//수정
		EmployeeVO employee01=employees.get(0);
		employee01.setEmployee_id(employee01.getEmployee_id());
		employee01.setPassword(employee01.getPassword()+"_U");
		employee01.setDept_no(0);
		employee01.setPosition_no(0);
		employee01.setCell_phone(0);
		employee01.setEmail(employee01.getEmail()+"_U");
		employee01.setAddress(employee01.getAddress()+"_U");
		employee01.setHoliday(0);
		employee01.setImg_code(employee01.getImg_code()+"_U");
		
		int upFlag=doUpdateController(employee01);
		assertThat(upFlag,is(1));
		
		
		 
	}
	
	
	@Test
	@Ignore
	public void addAndGet() throws Exception{
		//삭제
		for(EmployeeVO vo:employees) {
			doDelete(vo);
		}
		
		//단건입력
		for(EmployeeVO vo:employees) { 
			int flag=doInsert(vo);
			assertThat(flag, is(1)); 
		}
		
		//기존 정보와 비교
		for(EmployeeVO vo: employees) {
			EmployeeVO vsEmployee=doSelectOne(vo);
			checkUser(vo, vsEmployee);
		}
	}
	
	public int doUpdateController(EmployeeVO employee) throws Exception {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
	    LOG.debug("=doUpdateController()=");
		
	    MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/employee/doUpdate.do")
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
	    ResultActions resultActions = this.mockMvc.perform(createMessage)
	            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
	            .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.msgId", is("1")))
	      ;
	    String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

	    LOG.debug("=result=" + result);
	    // json --> message
	    Gson gson = new Gson();
	    Message outVO = gson.fromJson(result, Message.class);
	    LOG.debug("=message=" + outVO);
	    LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");

	    return Integer.valueOf(outVO.getMsgId());
	
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
	
	public EmployeeVO doSelectOne(EmployeeVO employee) throws Exception {
		MockHttpServletRequestBuilder  createMessage =MockMvcRequestBuilders.get("/employee/doSelectOne.do")
													.param("employee_id", employee.getEmployee_id())
													;
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.employee_id", is(employee.getEmployee_id())));

		String result=resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		LOG.debug("==========================");
		LOG.debug("=result="+result);
		LOG.debug("==========================");
		//json to User
		Gson gson= new Gson();
		EmployeeVO outVO=gson.fromJson(result, EmployeeVO.class);
		LOG.debug("==========================");
		LOG.debug("=outVo="+outVO);
		LOG.debug("==========================");
		return outVO;
	}
	
	public void doDelete(EmployeeVO employee) throws Exception {
		MockHttpServletRequestBuilder  createMessage =MockMvcRequestBuilders.get("/employee/doDelete.do")
													.param("employee_id", employee.getEmployee_id())
													;
		ResultActions resultActions = mockMvc.perform(createMessage)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().is2xxSuccessful())
                ;
		
		String result=resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
	
		LOG.debug("==========================");
		LOG.debug("=result="+result);
		LOG.debug("==========================");
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
