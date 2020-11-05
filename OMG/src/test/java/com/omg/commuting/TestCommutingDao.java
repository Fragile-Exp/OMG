package com.omg.commuting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.util.ArrayList;
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
import org.springframework.web.context.WebApplicationContext;

import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.domain.PresentState;
import com.omg.commuting.domain.State;
import com.omg.comutting.dao.CommutingDaoImpl;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})

public class TestCommutingDao {
	
	/**LOG*/
	final Logger LOG = LoggerFactory.getLogger(TestCommutingDao.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	CommutingDaoImpl commutingDao;
	
	List<Commuting> attendList;

	
	/**
	 * update 근무시간
	 * @throws Exception
	 */
	@Test
	public void rollingTest() throws Exception {
		
		int verify = 0;
		
		//1.조회/삭제
		Search search = new Search();
		attendList = commutingDao.doSelectList(search);
		
		for(Commuting vo : attendList) {
			verify += commutingDao.doDelete(vo);
		}
		
		//2.초기 데이터 주입
		verify+=commutingDao.doInit();
		
		//3. 출퇴근시간, 작업시간 업데이트
		attendList = commutingDao.doSelectList(search);
		for(Commuting vo : attendList) {
			vo.setAttendTime(StringUtil.formatDate("yyyyMMdd 090000"));
			vo.setLeaveTime(StringUtil.formatDate("yyyyMMdd 180000"));
			vo.setPresentState(PresentState.퇴근);
			vo.setState(State.조퇴);
			verify += commutingDao.doUpdate(vo);
			verify += commutingDao.doUpdateWorkTime(vo);
		}
		//4. 확인
		attendList = commutingDao.doSelectList(search);
		
	}
	

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("====================================");
		LOG.debug("=tearDown()=");
		LOG.debug("====================================");
	}

	@Test
	@Ignore
	public void beans() {
		LOG.debug("====================================");
		LOG.debug("=beans()=");
		LOG.debug("=context=" + context);
		LOG.debug("CommutingDaoImpl=" + commutingDao);
		LOG.debug("====================================");
	}

}
