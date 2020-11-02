package com.omg.commuting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
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

import com.omg.comutting.dao.Commuting;
import com.omg.comutting.dao.CommutingDaoImpl;
import com.omg.comutting.dao.PresentState;
import com.omg.comutting.dao.State;
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
	CommutingDaoImpl dao;
	
	List<Commuting> attendlist;
	
	@Test
	public void addAndGet() {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug("-addAndGet-");
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		int flag=0;
		for(Commuting vo : attendlist) {
			flag+=dao.doDelete(vo);
		}
		
		
		for(Commuting vo : attendlist) {
			flag+=dao.doUpsert(vo);
		}
		
		for(Commuting vo : attendlist) {
			flag+=dao.doUpsert(vo);
		}
		
		for(Commuting vo : attendlist) {
			dao.doSelectToday(vo);
		}
		
		assertThat(flag, is(15));
		
	}
	
	private void checkVO(Commuting orgVO, Commuting vsVO) {
		assertThat(orgVO.getSeq(), is(vsVO.getSeq()));
		assertThat(orgVO.getEmployeeId(), is(vsVO.getEmployeeId()));
		assertThat(orgVO.getName(), is(vsVO.getName()));
		assertThat(orgVO.getDeptNo(), is(orgVO.getDeptNo()));
		assertThat(orgVO.getAttendTime(), is(vsVO.getAttendTime()));
		assertThat(orgVO.getLeaveTime(), is(vsVO.getLeaveTime()));
		assertThat(orgVO.getPresentState(), is(vsVO.getPresentState()));
		assertThat(orgVO.getState(), is(vsVO.getState()));
		assertThat(orgVO.getWorkTime(), is(vsVO.getWorkTime()));
		assertThat(orgVO.getRegDt(), is(vsVO.getRegDt()));
		
		
		
	}
	
	@Before
	public void setUp() throws Exception {
		attendlist = Arrays.asList(
					new Commuting("jpark", "박재범", "10000")
					,new Commuting("sik", "식케이", "11000")
					,new Commuting("haon", "김하온", "12000")
					,new Commuting("woodie", "우디고", "13000")
					,new Commuting("donghyun", "서동현", "14000")
				);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("====================================");
		LOG.debug("=tearDown()=");
		LOG.debug("====================================");
	}

	@Test
	public void beans() {
		LOG.debug("====================================");
		LOG.debug("=beans()=");
		LOG.debug("=context=" + context);
		LOG.debug("CommutingDaoImpl=" + dao);
		LOG.debug("====================================");
	}

}
