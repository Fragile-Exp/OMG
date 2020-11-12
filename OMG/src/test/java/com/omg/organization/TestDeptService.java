package com.omg.organization;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import com.omg.organization.domain.DeptVO;
import com.omg.organization.service.DeptService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서 
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestDeptService {
final Logger LOG = LoggerFactory.getLogger(TestDeptService.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	DeptService service;
	
	// 픽스쳐
	private List<DeptVO> list;

	@Before
	public void setUp() throws Exception {
		LOG.debug(" === setUp === ");
		list = Arrays.asList(
				new DeptVO(10000,"omg",0),
				new DeptVO(11000,"전략기획본부",10000),
				new DeptVO(12000,"경영관리본부",10000),
				new DeptVO(13000,"기술개발본부",10000),
				new DeptVO(14000,"영업본부",10000),
				new DeptVO(13100,"연구소",13000),
				new DeptVO(13110,"제1연구소",13100),
				new DeptVO(13200,"기술부문",13000),
				new DeptVO(13210,"기술 1팀",13200)
				);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void totalTest() {
		int flag = 0;
		// 삭제
		for(DeptVO vo:list) {
			flag = service.doDelete(vo);
			
		}
		
		// 등록
		for(DeptVO vo:list) {
			flag = service.doInsert(vo);
			assertThat(flag, is(1));
			LOG.debug("부서 : "+vo);
		}
		
		// 조회
		List<DeptVO> posList = service.doSelectList();
		assertThat(posList.size(),is(9));

		
		// 수정
		DeptVO param = list.get(0);
		param.setDeptNm(param.getDeptNm()+"_U");
		
		flag = service.doUpdate(param);
		assertThat(flag, is(1));
		// 확인
		DeptVO vsPos = service.doSelectOne(param);
		checkDept(param, vsPos);
		
	}
	
	public void checkDept(DeptVO orgDept, DeptVO vsDept) {
		assertThat(orgDept.getDeptNo(), is(vsDept.getDeptNo()));
		assertThat(orgDept.getDeptNm(), is(vsDept.getDeptNm()));
		assertThat(orgDept.getUpDept(), is(vsDept.getUpDept()));
		
	}

	@Test
	@Ignore
	public void bean() {
		LOG.debug("context : "+context);
		LOG.debug("service : "+service);
	}

}
