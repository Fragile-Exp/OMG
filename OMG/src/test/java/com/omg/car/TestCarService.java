package com.omg.car;

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

import com.omg.car.domain.CarVO;
import com.omg.car.service.CarService;




@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestCarService {
	final Logger LOG = LoggerFactory.getLogger(TestCarService.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	CarService  carService;
	
	private List<CarVO> list;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug(" === setUp === ");
		list = Arrays.asList(
						new CarVO("125가 4568","AVANTE","LPN","ID01",0,"2020-11-20","출장"),
						new CarVO("24마 4895","SONATA","가솔린","ID02",1,"2020-11-20","수리"),
						new CarVO("34쌍 7985","GRANDEUR","디젤","ID03",2,"2020-11-20","업무"),
						new CarVO("35쌍 1956","PALISADE","전기","ID04",3,"2020-11-20","폐차")
				);   
	
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=====================");
		LOG.debug("==tearDown==");
		LOG.debug("=====================");
	}

	@Test
	public void TotalTest() {
		int flag = 0;
		//삭제 
		for(CarVO vo : list) {
			flag =carService.doDelete(vo);
			LOG.debug("flag=="+flag);
			assertThat(flag,is(1));
		}
	
		//입력
		for(CarVO vo : list) {
			LOG.debug("vo=="+vo);
			flag =carService.doInsert(vo);
			assertThat(flag, is(1));
			
		}

		
		CarVO param = list.get(0);
		param.setKind(param.getKind()+"_U");
		param.setRentDay("2020-11-30");
		param.setReason(param.getReason()+"_U");
		
		//수정
		LOG.debug("=param="+param);
		flag = carService.doUpdate(param);
		assertThat(flag, is(1));
		
		//단건검색
		CarVO vsPos = carService.doSelectOne(param);
		LOG.debug("=vs="+vsPos);
		assertThat(flag, is(1));
		
		//전체 조회
		List<CarVO> listPos = (List<CarVO>) carService.doSelectList();
		assertThat(listPos.size(), is(4));
		for(CarVO vo : listPos ) {
			LOG.debug("=vo="+vo);
			
		}
		
	}
	

	
	@Test
	@Ignore
	public void test() {
		fail("Not yet implemented");
	}

}
