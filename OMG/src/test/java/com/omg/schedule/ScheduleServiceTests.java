package com.omg.schedule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.omg.schedule.domain.ScheduleVO;
import com.omg.schedule.service.ScheduleService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
				   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ScheduleServiceTests {
    private final Logger LOG = LoggerFactory.getLogger(ScheduleDaoTests.class);
    
    @Autowired
    WebApplicationContext ctx;
    
    @Autowired
    @Qualifier("scheduleService")
    private ScheduleService service;
    
    private ScheduleVO inVO;
    
    @Before
    public void setup() throws Exception {
	LOG.debug("setup.....");
	
	inVO = new ScheduleVO();
	inVO.setDeptNo(10);
	inVO.setEmployeeId("user01");
	inVO.setCategoryId(10);
	inVO.setTimeStatus(0);
	inVO.setTitle("테스트 제목");
	inVO.setContent("테스트 내용");
	inVO.setStartDt("2020-01-01 00:00");
	inVO.setEndDt("2020-01-01 01:00");
    }
    
    @Test
    public void totalTest() {
	service.doInsert(inVO); //등록
	
	inVO.setScheduleNo(28);
	service.doSelectOne(inVO); //단건 검색
	service.doUpdate(inVO);	//수정
	
	inVO.setDeptNo(0);
	service.doSelectList(inVO); //다건조회
    }

}
