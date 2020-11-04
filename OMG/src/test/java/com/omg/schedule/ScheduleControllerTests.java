package com.omg.schedule;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.omg.schedule.service.ScheduleService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		       "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ScheduleControllerTests {
    final Logger LOG = Logger.getLogger(ScheduleControllerTests.class);
    @Autowired
    private WebApplicationContext ctx;
    
    private MockMvc mockMvc;

    @Before
    public void setup() {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    /**
     * 리스트 테스트
     * depNo = 0(전체검색)
     * @throws Exception
     * @author 박정민
     * @Date 2020-11-03
     */
    @Test
    @Ignore
    public void testList() throws Exception {
	LOG.debug(mockMvc.perform(MockMvcRequestBuilders.get("/schedule/list")
		.param("deptNo", "0")
		).andReturn().getModelAndView().getModelMap());
    }
    
    @Test
    public void testInsert() throws Exception {
	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/schedule/insert")
		.param("deptNo", "0")
		.param("employeeId", "Test_User")
		.param("categoryId", "10")
		.param("timeStatus", "0")
		.param("title", "테스트 제목")
		.param("content", "테스트 내용")
		.param("startDt", "2020-01-01 00:00")
		.param("endDt", "2020-01-01 00:00")
		).andReturn().getModelAndView().getViewName();
	
	LOG.debug(resultPage);
    }
    
}
