package com.omg.schedule;

import java.util.List;

import org.junit.Ignore;
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
    Logger log = LoggerFactory.getLogger(ScheduleDaoTests.class);
    
    @Autowired
    WebApplicationContext ctx;
    
    @Autowired
    @Qualifier("scheduleService")
    private ScheduleService service;
    
    /**
     * 등록 테스트
     * 테스트완료 2020-11-03
     * @author 박정민
     */
    @Test
    @Ignore
    public void testInsert() {
	ScheduleVO inVO = new ScheduleVO();
	inVO.setDeptNo(10);
	inVO.setEmployeeId("user2");
	inVO.setCategoryId(10);
	inVO.setTimeStatus(0);
	inVO.setTitle("테스트 제목1");
	inVO.setContent("테스트 내용1");
	inVO.setStartDt("2020-01-01 00:00");
	inVO.setEndDt("2020-01-01 01:00");
	
	int flag = service.doInsert(inVO);
	
	log.debug("flag: " + flag);
    }

    /**
     * 삭제 테스트
     * 테스트완료 2020-11-03
     * @author 박정민
     */
    @Test
    public void testDelete() {
	int scheduleNo = 32;
	int flag = service.doDelete(scheduleNo);
	
	log.debug("flag: " + flag);
    }
    
    /**
     * 수정 및 단건검색 테스트
     * 테스트완료 2020-11-03
     * @author 박정민
     */
    @Test
    @Ignore
    public void testUpdateAndSelectOne() {
	int scheduleNo = 32;
	ScheduleVO inVO = service.doSelectOne(scheduleNo); //doSelectOne 테스트
	
	inVO.setTitle("수정된 제목");
	inVO.setContent("수정된 내용");
	inVO.setStartDt("2010-01-01 00:00");
	inVO.setEndDt("2010-01-01 12:00");
	
	int flag = service.doUpdate(inVO);
	
	log.debug("flag: " + flag);	
    }
    
    /**
     * 리스트 테스트
     * 테스트완료 2020-11-03
     * @author 박정민
     */
    @Test
    @Ignore
    public void testSelectList() {
	int deptNo = 30;
	List<ScheduleVO> list = service.doSelectList(deptNo);
	
	for(ScheduleVO vo : list) {
	    log.debug("vo: " + vo);
	}
    }

}
