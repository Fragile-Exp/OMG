package com.omg.schedule;

import java.util.List;

import org.junit.Before;
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

import com.omg.schedule.dao.ScheduleCatDao;
import com.omg.schedule.domain.ScheduleCatVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
				   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ScheduleDaoCatTests {
    private final Logger LOG = LoggerFactory.getLogger(ScheduleDaoCatTests.class);
    
    @Autowired
    WebApplicationContext ctx;
    
    @Autowired
    @Qualifier("scheduleCatDao")
    private ScheduleCatDao dao;
    
    private ScheduleCatVO inVO;
    
    @Before
    public void setup() throws Exception {
	LOG.debug("setup.....");
	
	inVO = new ScheduleCatVO();
	inVO.setCategoryId(0);
	inVO.setCategoryNm("테스트 팀");
    }
    
    @Test
    public void totalTest() {
	dao.doInsert(inVO); //등록
	
	dao.doSelectOne(inVO); //단건조회
	inVO.setCategoryNm("수정된 테스트 팀");
	dao.doUpdate(inVO); //수정
	
	dao.doSelectList(); //다건조회
	
	dao.doDelete(inVO); //삭제
    }
    
    
}
