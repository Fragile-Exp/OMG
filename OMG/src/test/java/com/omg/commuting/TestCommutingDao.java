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

import com.omg.cmn.Criteria;
import com.omg.cmn.DTO;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.domain.PresentState;
import com.omg.commuting.domain.State;
import com.omg.comutting.dao.CommutingDaoImpl;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//?��?��?�� ?��?��?�� 컨텍?��?�� ?��?��?��?��?��?�� JUnit기능 ?��?��
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

	
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=setUp()=");
	}
	
	
	@Test
	public void rollingTest() throws Exception {
		
		doSelectList();
		doDelete();
		
		doInit();
		
		doSelectList();
		doUpdate();
		
	}
	
	
	
	/**
	 * getAll
	 */
	private void doSelectList() {
		attendList = commutingDao.getAll();
	}
	
	/**
	 * init data insert
	 */
	private void doInit() {
		commutingDao.doInit();
	}
	
	/**
	 * delete All
	 */
	private void doDelete() {
		int flag = 0;
		for(Commuting vo : attendList) {
			flag += commutingDao.doDelete(vo);
		}
		
		assertThat(flag, is(attendList.size()));
	}
		
	/**
	 * update All
	 */
	private void doUpdate() {
		int flag = 0;
		for(Commuting vo : attendList) {
			vo.setAttendTime(StringUtil.formatDate("yyyyMMdd 090000"));
			vo.setLeaveTime(StringUtil.formatDate("yyyyMMdd 180000"));
			vo.setState(State.조퇴);
			flag += commutingDao.doUpdate(vo);
			flag += commutingDao.doUpdateWorkTime(vo);
		}
		
		assertThat(flag, is(attendList.size()*2));
	}
	
	/**
	 * selectOne
	 * @throws Exception
	 */
	private void doSelectOne() throws Exception {
		for(Commuting vo : attendList) {
			Commuting inVO = (Commuting) commutingDao.doSelectOne(vo);
			checkCommuting(vo, inVO);
		}
	}
	
	private void checkCommuting(Commuting org, Commuting vs) throws Exception {
		assertThat(org.getSeq(), is(vs.getSeq()));
		assertThat(org.getEmployeeId(), is(vs.getEmployeeId()));
		assertThat(org.getName(), is(vs.getName()));
		assertThat(org.getDeptNo(), is(vs.getDeptNo()));
		assertThat(org.getAttendTime(), is(vs.getAttendTime()));
		assertThat(org.getLeaveTime(), is(vs.getLeaveTime()));
		assertThat(org.getPresentState(), is(vs.getPresentState()));
		assertThat(org.getState(), is(vs.getState()));
		assertThat(org.getWorkTime(), is(vs.getWorkTime()));
		assertThat(org.getRegDt(), is(vs.getRegDt()));
	}
	@After
	public void tearDown() throws Exception {
		LOG.debug("=tearDown()=");
	}

	@Test
	public void beans() {
		LOG.debug("====================================");
		LOG.debug("=beans()=");
		LOG.debug("=context=" + context);
		LOG.debug("CommutingDaoImpl=" + commutingDao);
		LOG.debug("====================================");
	}

}
