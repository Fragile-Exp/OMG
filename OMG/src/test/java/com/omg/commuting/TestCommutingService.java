package com.omg.commuting;

import java.util.List;

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
import com.omg.cmn.Search;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.service.CommutingService;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //application context 공유를 위함
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
}) //배열 가능
public class TestCommutingService {
	
	/** LOG */
	final Logger LOG = LoggerFactory.getLogger(TestCommutingService.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	CommutingService commutingService;
	
	//fixture
	List<Commuting> attendList;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug(">setUp()>");
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
	
	
	private void makeList() {
		Criteria criteria = new Criteria();
		attendList = commutingService.doSelectList(criteria);
	}
	
	@Test
	public void rollingTest() {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug(">rollingTest()>");
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		
		//makeList();
//		for(Commuting vo : attendList) {
//			commutingService.doDelete(vo);
//		}
//		
		commutingService.doInit();
//		
//		makeList();
//		
//		for(Commuting vo : attendList) {
//				commutingService.doUpdateAttendTime(vo);
//				
//			
//		}
//		
//		makeList();
//		
//		for(Commuting vo : attendList) {
//				commutingService.doUpdateLeaveTime(vo);
//		}
//		
//		commutingService.getAll();		
	}
	
	@Test
	public void beans() {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug(">webApplicationContext>"+webApplicationContext);
		LOG.debug(">commutingService>"+commutingService);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
	}

}
