package com.omg.document;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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


import com.omg.document.dao.DocumentDaoImpl;
import com.omg.document.domain.DocumentVO;
import com.omg.document.service.DocumentService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestDocumentService {
	
	final Logger LOG = LoggerFactory.getLogger(TestDocumentService.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	DocumentService  documentService;
	
	private List<DocumentVO> list;
	
//	@Before
//	public void setUp() throws Exception {
//		LOG.debug(" === setUp === ");
//		list = Arrays.asList(
//						new DocumentVO("W_0001","ID01",1,"워드01","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//						new DocumentVO("E_0002","ID01",1,"엑셀01","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//						new DocumentVO("E_0003","ID01",1,"엑셀02","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//						new DocumentVO("E_0004","ID01",1,"엑셀03","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//						new DocumentVO("E_0005","ID01",1,"엑셀04","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//						new DocumentVO("E_0006","ID01",1,"엑셀05","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//						new DocumentVO("P_0003","ID01",1,"ppt01","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//						new DocumentVO("E_0001","ID02",2,"엑셀01","2020-11-19","문서설명내용",1,"ID99(관리자)"),
//						new DocumentVO("P_0001","ID03",3,"ppt01","2020-11-19","문서설명내용",2,"ID99(관리자)")
//				);   
//		
//	}

	
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("=====================");
		LOG.debug("==tearDown==");
		LOG.debug("=====================");
	
	}

	@Test
	@Ignore
	public void Insert() {
		int flag = 0;
		for(DocumentVO vo : list) {
			LOG.debug("vo=="+vo);
			flag =documentService.doInsert(vo);
			assertThat(flag, is(1));
			
		}
	}
	
	@Test
	public void TotalTest() {
		int flag = 0;
		//삭제 
		for(DocumentVO vo : list) {
			flag =documentService.doDelete(vo);
			assertThat(flag, is(1));
		}
	
		//입력
		for(DocumentVO vo : list) {
			LOG.debug("vo=="+vo);
			flag =documentService.doInsert(vo);
			assertThat(flag, is(1));
			
		}

		
		//수정 데이터
		DocumentVO param01 = list.get(0);
		param01.setTitle(param01.getTitle()+"_U");
		param01.setdDay("2020-11-20");
		param01.setDocumentCont(param01.getDocumentCont()+"_U");
		
		//수정
		LOG.debug("=param="+param01);
		flag = documentService.doUpdate(param01);
		assertThat(flag, is(1));
		
		//단건검색
		DocumentVO doPos = documentService.doSelectOne(param01);
		assertThat(doPos, is(notNullValue()));
		LOG.debug("=vo="+doPos);
		
		
		//전체 검색 
		List<DocumentVO> listPos = (List<DocumentVO>) documentService.doSelectList();
		assertThat(listPos.size(), is(5));
		for(DocumentVO vo : listPos ) {
			LOG.debug("=vo="+vo);
			
		}
		
		
		//사번을 통해 문서 검색 
		param01.setEmployeeId("ID01");
		List<DocumentVO> empId = (List<DocumentVO>) documentService.doempIdSelectList(param01);
		assertThat(empId.size(), is(3));
		for(DocumentVO vo : empId ) {
			LOG.debug("=vo="+vo);
			
		}
		
	}
	
	
	public void checkNote(DocumentVO orgNote, DocumentVO vsNote) {
		assertThat(orgNote.getDocumentId(), is(vsNote.getDocumentId()));
		assertThat(orgNote.getEmployeeId(), is(vsNote.getEmployeeId()));
		assertThat(orgNote.getKind(), is(vsNote.getKind()));
		assertThat(orgNote.getTitle(), is(vsNote.getTitle()));
		assertThat(orgNote.getdDay(), is(vsNote.getdDay()));
		assertThat(orgNote.getDocumentCont(), is(vsNote.getDocumentCont()));
		assertThat(orgNote.getDocumentSet(), is(vsNote.getDocumentSet()));
		assertThat(orgNote.getOkUser(), is(vsNote.getOkUser()));
		
	}
	
	
	@Test
	@Ignore
	public void bean() {
		LOG.debug("context : "+context);
		LOG.debug("service : "+documentService);
	}
	
}
