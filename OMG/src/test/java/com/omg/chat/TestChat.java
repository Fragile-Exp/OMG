package com.omg.chat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import com.omg.chat.domain.ChattingRoom;
import com.omg.chat.service.ChattingRoomService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서 
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestChat {
	final Logger LOG = LoggerFactory.getLogger(TestChat.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	ChattingRoomService service;

	@Before
	public void setUp() throws Exception {
		LOG.debug("== setUp ==");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		List<ChattingRoom> list = service.doSelectList();
		ChattingRoom room = list.get(0);
		for(ChattingRoom vo : list) {
			LOG.debug("vo = " + vo);
		}
		
		ChattingRoom outVO = service.doSelectOne(room);
		assertThat(outVO.getRoomNo(), is(room.getRoomNo()));
		
	}

	@Test
	public void bean() {
		LOG.debug("context : "+context);
		LOG.debug("service : "+service);
	}

}
