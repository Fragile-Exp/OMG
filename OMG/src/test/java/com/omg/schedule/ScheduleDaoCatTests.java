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

import com.omg.schedule.dao.ScheduleCatDao;
import com.omg.schedule.domain.ScheduleCatVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
				   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ScheduleDaoCatTests {
    Logger log = LoggerFactory.getLogger(ScheduleDaoCatTests.class);
    
    @Autowired
    WebApplicationContext ctx;
    
    @Autowired
    @Qualifier("scheduleCatDao")
    private ScheduleCatDao dao;
    
    /**
     * 등록 테스트
     * 테스트완료 2020-11-02
     * @author 박정민
     */
    @Test
    @Ignore
    public void testInsert() {
	ScheduleCatVO inVO = new ScheduleCatVO();
	inVO.setCategoryId(40);
	inVO.setCategoryNm("팀4");
	
	int flag = dao.doInsert(inVO);
	
	log.debug("flag: " + flag);
    }
    
    /**
     * 삭제 테스트
     * 테스트완료 2020-11-02
     * @author 박정민
     */
    @Test
    @Ignore
    public void testDelete() {
	int categoryId = 40;
	int flag = dao.doDelete(categoryId);
	
	log.debug("flag: " + flag);
    }
    
    /**
     * 수정 및 단건검색 테스트
     * 테스트완료 2020-11-02
     * @author 박정민
     */
    @Test
    @Ignore
    public void testUpdateAndSelectOne() {
	int categoryId = 30;
	ScheduleCatVO inVO = dao.doSelectOne(categoryId);
	inVO.setCategoryNm("3팀");
	
	int flag = dao.doUpdate(inVO);
	
	log.debug("flag: " + flag);
    }
    
    /**
     * 리스트 테스트
     * 테스트완료 2020-11-02
     * @author 박정민
     */
    @Test
    public void testSelectList() {
	List<ScheduleCatVO> list = dao.doSelectList();
	
	for(ScheduleCatVO vo : list) {
	    log.debug("vo: " + vo);
	}
    }
}
