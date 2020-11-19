package com.omg.commuting;

import java.util.Arrays;
import java.util.List;

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

import com.omg.commuting.domain.Commuting;
import com.omg.commuting.domain.PresentState;
import com.omg.commuting.domain.State;
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
	
	/*
	 * INSERT INTO commuting ( seq, employee_id, name, dept_no, attend_time,
	 * leave_time, present_state, state, work_time, reg_dt ) VALUES ( #{seq},
	 * #{employeeId}, #{name}, #{deptNo}, TO_DATE(#{attendTime},'yyyymmddHH24MISS'),
	 * TO_DATE(#{leaveTime},'yyyymmddHH24MISS'), #{presentStateIntValue},
	 * #{stateIntValue}, #{workTime}, TO_DATE(#{regDt},'yyyymmddHH24MISS') )
	 */            
	@Before
	public void setUp() throws Exception {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug(">setUp()>");
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		
		attendList = Arrays.asList(
					
					new Commuting("2020-11-18", "ASIA01","박수석", "14100","20201118083000","20201118194500"
							,PresentState.퇴근,State.정상,"0","20201118083000")
					
					,new Commuting("2020-11-18", "ASIA02","박첵임", "14100","20201118083000","20201118194500"
							,PresentState.퇴근,State.정상,"0","20201118083000")
					
					,new Commuting("2020-11-18", "ASIA03","박선임", "14100","20201118083000","20201118194500"
							,PresentState.퇴근,State.정상,"0","20201118083000")
					
					,new Commuting("2020-11-18", "ASIA04","박사원", "14100","20201118083000","20201118194500"
							,PresentState.퇴근,State.정상,"0","20201118083000")
					
					,new Commuting("2020-11-18", "ASIA05","박수습", "14100","20201118083000","20201118194500"
							,PresentState.퇴근,State.정상,"0","20201118083000")
					
				);
	}
	
	@Test
	public void rollingTest() {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug(">rollingTest()>");
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		
		for(Commuting vo : attendList) {
			commutingService.doInsert(vo);
		}
		
	}
	
	@Test
	public void beans() {
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug(">webApplicationContext>"+webApplicationContext);
		LOG.debug(">commutingService>"+commutingService);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
	}

}
