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
	CommutingDaoImpl dao;
	
	List<Commuting> attendlist;

	
	/**
	 * update 근무시간
	 * @throws Exception
	 */
	
	
	@Test
	@Ignore
	public void totalTest() throws Exception {
		
		//doDeleteALL();
		
		doInsert();
		
		doUpdateSelectOne();
		
		//doUpdateWorkTime();
		
		//doSelectList();
		
	}
	
	@Test
	public void doInit() {
		int verify = dao.doInit();
		assertThat(verify, is(10));
	}
	
	
	public void doUpdateWorkTime() throws Exception {
		int verify = 0;
		for(Commuting vo : attendlist) {
			verify += dao.doUpdateWorkTime(vo);
		}
		
		assertThat(verify, is(10));
	}
	
	public void doSelectList() throws Exception {
		Search search = new Search("10", "11000"); 
		dao.doSelectList(search);
	}
	
	/**
	 * 출근 , 퇴근 기록 update 
	 * @throws Exception
	 */
	public void doUpdateSelectOne() throws Exception {
		
		int verify = 0;
		
		for(Commuting cVO : attendlist) {
			
			cVO.setLeaveTime(StringUtil.formatDate("yyyyMMdd 180000"));
			cVO.setPresentState(PresentState.퇴근);
			cVO.setState(State.정상);
			
			verify = dao.doUpdate(cVO);
			assertThat(verify, is(1));
			
			dao.doSelectOne(cVO);
		}
		
	}
	
	/**
	 * 출근전 사원 default 등록하기
	 * @throws Exception
	 */
	public void doInsert() throws Exception {
		
		int flag = 0;
		for(Commuting vo : attendlist) {
			LOG.debug(vo.toString());
			flag += dao.doInsert(vo);
		}
		assertThat(flag, is(10));
		
	}
	
	/**
	 * 단건조회
	 * @throws Exception
	 */
	public void doSelectOne() throws Exception {
		
		for(Commuting vo : attendlist) {
			Commuting inVO =(Commuting) dao.doSelectOne(vo);
		}
		
		LOG.debug("list : " + attendlist);	
	}
	
	/**
	 * 삭제
	 * @throws Exception
	 */
	public void doDeleteALL() throws Exception {
		for(DTO vo : attendlist) {
			dao.doDelete(vo);
		}
		
	}

	@Before
	public void setUp() throws Exception {
		attendlist = Arrays.asList(
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID01", "병운", "10000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID02", "성현", "11000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID03", "유비", "12000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID04", "정민", "13000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID05", "기태", "14000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID06", "광민", "10000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID07", "현수", "11000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID08", "성민", "12000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID09", "영민", "13000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd")),
					new Commuting(StringUtil.formatDate("yyyy-MM-dd"), "ID10", "국민", "14000", StringUtil.formatDate("yyyyMMdd 090000"), StringUtil.formatDate(""), PresentState.대기중, State.정상, "", StringUtil.formatDate("yyyyMMdd"))
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
