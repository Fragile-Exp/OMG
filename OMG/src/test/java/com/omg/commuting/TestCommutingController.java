package com.omg.commuting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.service.CommutingService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class TestCommutingController {
	/**LOG*/
	final Logger LOG = LoggerFactory.getLogger(TestCommutingController.class);
	
	@Autowired //테스트 컨택스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext webApplicationContext;
	
	@Autowired
	CommutingService commutingService; 
	
	
	MockMvc mockMvc;
	
	//테스트 데이터
	List<Commuting> attends;
	
	private void makeList() {
		Search search = new Search("", "");
		attends = commutingService.doSelectList(search);
	}
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug(">mockMvc>" + mockMvc);
		
		
	}
	
	@Test
	@Ignore
	public void doInit() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/commuting/doInit.do");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				//.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
				//.andExpect(jsonPath("$.msgId", is("1")));
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("=result=" + result);

		// json --> message
		Gson gson = new Gson();
		Message message = gson.fromJson(result, Message.class);
		LOG.debug("=message=" + message);
	}
	
	@Test
	@Ignore
	public void doDelete() throws Exception {
		makeList();
		Commuting attend = attends.get(0);
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/commuting/doDelete.do")
				.param("seq", attend.getSeq())
				.param("employeeId", attend.getEmployeeId());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				//.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
				//.andExpect(jsonPath("$.msgId", is("1")));
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("=result=" + result);
		Gson gson = new Gson();
		Message message = gson.fromJson(result, Message.class);
		LOG.debug("=message=" + message);
			
	}
	
	@Test
	@Ignore
	public void doUpdateAttendTime() throws Exception {
		makeList();
		Commuting attend = attends.get(0);
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/commuting/doUpdateAttendTime.do")
				.param("seq", attend.getSeq())
				.param("employeeId", attend.getEmployeeId());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("=result=" + result);

		Gson gson = new Gson();
		Message message = gson.fromJson(result, Message.class);
		assertThat(message.getMsgId(), is(1+""));
		
		makeList();
	}
	
	@Test
	@Ignore
	public void doUpdateLeaveTime() throws Exception {
		makeList();
		Commuting attend = attends.get(0);
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/commuting/doUpdateLeaveTime.do")
				.param("seq", attend.getSeq())
				.param("employeeId", attend.getEmployeeId())
				.param("attendTime", attend.getAttendTime())
				.param("presentState", attend.getPresentState()+"")
				.param("state", attend.getState()+"");
				
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("=result=" + result);

		Gson gson = new Gson();
		Message message = gson.fromJson(result, Message.class);
		assertThat(message.getMsgId(), is(1+""));
		
		makeList();
	}
	
	@Test
	@Ignore
	public void doSelectList() throws Exception {
		Search search = new Search("","",10,1);
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/commuting/doSelectList.do")
				.param("searchDiv", search.getSearchDiv())
				.param("searchWord", search.getSearchWord())
				.param("pageSize", search.getPageSize()+"")
				.param("pageNum", search.getPageNum()+"");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("=result=" + result);
	}
	
	@Test
	public void doSelectMyList() throws Exception {
		makeList();
		Commuting attend = attends.get(0);
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/commuting/doSelectMyList.do")
				.param("employeeId", attend.getEmployeeId())
				.param("seq", attend.getSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("=result=" + result);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void beans() {
		
		LOG.debug("******************************************************");
		LOG.debug("=WebApplicationContext="+webApplicationContext);
		LOG.debug("=BoardService="+commutingService);
		LOG.debug("******************************************************");
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(commutingService, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
		
	}

}
