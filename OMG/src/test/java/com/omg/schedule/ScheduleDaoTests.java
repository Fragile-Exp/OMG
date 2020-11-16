package com.omg.schedule;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.omg.cmn.Criteria;
import com.omg.schedule.dao.ScheduleDao;
import com.omg.schedule.domain.ScheduleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
				    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ScheduleDaoTests {
    private final Logger LOG = Logger.getLogger(ScheduleDaoTests.class);

    @Autowired
    WebApplicationContext ctx;

    @Autowired
    @Qualifier("scheduleDao")
    private ScheduleDao dao;

    private ScheduleVO inVO;
    private Criteria cri;

    @Before
    public void setup() throws Exception {
	LOG.debug("setup.....");

	inVO = new ScheduleVO();
	inVO.setDept_no(10);
	inVO.setEmployee_id("user01");
	inVO.setCategory_id(10);
	inVO.setTitle("테스트 제목");
	inVO.setContent("테스트 내용");
	inVO.setStart_dt("2020-01-01 00:00");
	inVO.setEnd_dt("2020-01-01 01:00");
    }

    @Test
    @Ignore
    public void totalTest() {
	dao.doInsert(inVO); //등록
	
	inVO.setSchedule_no(28);
	dao.doSelectOne(inVO); //단건 검색
	dao.doUpdate(inVO);	//수정
	
	inVO.setDept_no(0);
	dao.doSelectList(cri); //다건조회
    }
    
    @Test
    public void testPaging() {
	Criteria cri = new Criteria();
	//10개씩 1페이지
	cri.setPageNum(1);
	cri.setAmount(10);
	
	List<ScheduleVO> list = dao.doSelectList(cri);
	list.forEach(schedule -> LOG.debug(schedule));
    }

}
