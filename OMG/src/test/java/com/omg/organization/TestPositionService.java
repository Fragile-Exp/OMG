package com.omg.organization;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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

import com.omg.organization.domain.PositionVO;
import com.omg.organization.service.PositionService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서 
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestPositionService {
	final Logger LOG = LoggerFactory.getLogger(TestPositionService.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	PositionService service;
	
	// 픽스쳐
	private List<PositionVO> list;

	@Before
	public void setUp() throws Exception {
		LOG.debug(" === setUp === ");
		list = Arrays.asList(
				new PositionVO(11310,"수석",11300),
				new PositionVO(11320,"책임",11310),
				new PositionVO(11330,"선임",11320),
				new PositionVO(11331,"사원",11330),
				new PositionVO(11332,"인턴",11331)
				);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void totalTest() {
		int flag = 0;
		// 삭제
		for(PositionVO vo:list) {
			flag = service.doDelete(vo);
			assertThat(flag, is(1));
			
		}
		
		// 등록
		for(PositionVO vo:list) {
			flag = service.doInsert(vo);
			assertThat(flag, is(1));
			LOG.debug("직급 : "+vo);
		}
		
		// 조회
		List<PositionVO> posList = service.doSelectList();
		assertThat(posList.size(),is(10));

		
		// 수정
		PositionVO param = list.get(0);
		param.setPositionNm(param.getPositionNm()+"_U");
		
		flag = service.doUpdate(param);
		assertThat(flag, is(1));
		// 확인
		PositionVO vsPos = service.doSelectOne(param);
		checkPosition(param, vsPos);
		
	}
	
	public void checkPosition(PositionVO orgPos, PositionVO vsPos) {
		assertThat(orgPos.getPositionNo(), is(vsPos.getPositionNo()));
		assertThat(orgPos.getPositionNm(), is(vsPos.getPositionNm()));
		assertThat(orgPos.getUpPosition(), is(vsPos.getUpPosition()));
		
	}

	@Test
	//@Ignore
	public void bean() {
		LOG.debug("context : "+context);
		LOG.debug("service : "+service);
	}

}
