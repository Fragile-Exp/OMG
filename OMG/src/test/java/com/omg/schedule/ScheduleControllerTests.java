package com.omg.schedule;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ScheduleControllerTests {
    private final Logger LOG = Logger.getLogger(ScheduleControllerTests.class);
    
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    /**
     * ?ùº?†ï ?ì±Î°? ?Öå?ä§?ä∏
     * 
     * @throws Exception
     * @author Î∞ïÏ†ïÎØ?
     */
    @Test
    @Ignore
    public void testInsert() throws Exception {
	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/schedule/insert.do")
		.param("deptNo", "10")
		.param("employeeId", "Test_User")
		.param("categoryId", "10")
		.param("timeStatus", "0")
		.param("title", "?Öå?ä§?ä∏ ?†úÎ™?")
		.param("content", "?Öå?ä§?ä∏ ?Ç¥?ö©")
		.param("startDt", "2020-01-01 00:00")
		.param("endDt", "2020-01-01 00:00")
		).andReturn().getModelAndView().getViewName();

	LOG.debug(resultPage);
    }

    /**
     * ?ùº?†ï ?Ç≠?†ú ?Öå?ä§?ä∏
     * 
     * @throws Exception
     * @author Î∞ïÏ†ïÎØ?
     */
    @Test
    @Ignore
    public void testDelete() throws Exception {
	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/schedule/delete.do")
		.param("scheduleNo", "37")
		).andReturn().getModelAndView().getViewName();

	LOG.debug(resultPage);
    }

    /**
     * ?ùº?†ï ?àò?†ï ?Öå?ä§?ä∏
     * 
     * @throws Exception
     * @author Î∞ïÏ†ïÎØ?
     */
    @Test
    @Ignore
    public void testUpdate() throws Exception {
	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/schedule/update.do")
		.param("categoryId", "10")
		.param("timeStatus", "0")
		.param("title", "?Öå?ä§?ä∏ ?†úÎ™?")
		.param("content", "?Öå?ä§?ä∏ ?Ç¥?ö©")
		.param("startDt", "2020-11-09")
		.param("endDt", "2020-11-09")
		.param("scheduleNo", "47")
		).andReturn().getModelAndView().getViewName();

	LOG.debug(resultPage);
    }

    /**
     * ?ùº?†ï ?Ñ†?Éù ?Öå?ä§?ä∏
     * 
     * @throws Exception
     * @author Î∞ïÏ†ïÎØ?
     */
    @Test
    @Ignore
    public void testSelectOne() throws Exception {
	LOG.debug(mockMvc.perform(MockMvcRequestBuilders.get("/schedule/get.do")
		.param("scheduleNo", "47")
		).andReturn().getModelAndView().getModelMap());
    }

    /**
     * ?ùº?†ï Î™©Î°ù?ôî ?Öå?ä§?ä∏ depNo = 0(?†ÑÏ≤¥Í??Éâ)
     * 
     * @throws Exception
     * @author Î∞ïÏ†ïÎØ?
     */
    @Test
    public void testList() throws Exception {
	LOG.debug(mockMvc.perform(MockMvcRequestBuilders.get("/schedule/list.do")
		.param("pageNum", "1")
		.param("amount", "10")
		).andReturn().getModelAndView().getModelMap());
    }

}
