package com.omg.note;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
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

import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.note.domain.NoteVO;
import com.omg.note.service.NoteService;
import com.omg.organization.domain.DeptVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서 
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestNoteService {
	final Logger LOG = LoggerFactory.getLogger(TestNoteService.class);

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	NoteService service;
	
	// 픽스쳐
	private List<NoteVO> list;
		
	@Before
	public void setUp() throws Exception {
		LOG.debug(" === setUp === ");
		list = Arrays.asList(
				new NoteVO(10001,1,"admin","관리자",1,"ID01",null,"유비_01","쪽지1_1","쪽지 전송테스트 1_1",0),
				new NoteVO(10002,1,"admin","관리자",1,"ID02",null,"유비_02","쪽지1_2","쪽지 전송테스트 1_2",0),
				new NoteVO(10003,1,"admin","관리자",1,"ID03",null,"유비_03","쪽지1_3","쪽지 전송테스트 1_3",0),
				new NoteVO(10004,1,"admin","관리자",1,"ID02",null,"유비_02","쪽지2","쪽지 전송테스트 2",0),
				new NoteVO(10005,1,"admin","관리자",1,"ID03",null,"유비_03","쪽지3","쪽지 전송테스트 3",0),
				new NoteVO(10001,1,"ID04","유비_04",1,"admin",null,"관리자","쪽지4","쪽지 전송테스트 4",0),
				new NoteVO(10002,1,"ID05","유비_05",1,"admin",null,"관리자","쪽지5","쪽지 전송테스트 5",0),
				new NoteVO(10003,1,"ID06","유비_06",1,"admin",null,"관리자","쪽지6","쪽지 전송테스트 6",0),
				new NoteVO(10004,1,"ID04","유비_04",1,"admin",null,"관리자","쪽지4_2","쪽지 전송테스트 4_2",0),
				new NoteVO(10005,1,"ID05","유비_05",1,"admin",null,"관리자","쪽지5_2","쪽지 전송테스트 5_2",0)
				);

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void totalTest() {
		int flag = 0;
		Message message;
		// 삭제(휴지통 이동)
		for(NoteVO vo:list) {
			NoteVO inVO = new NoteVO();
			// 보낸 쪽지함 지우기
			inVO.setNoteNo(vo.getNoteNo());
			inVO.setNoteDiv(vo.getNoteDiv());
			inVO.setEmployeeId(vo.getSenderId());
			delete(inVO);
			// 받은 쪽지함 지우기
			inVO.setNoteDiv(2);
			inVO.setEmployeeId(vo.getReceiveId());
			delete(inVO);
			
		}
		// 삭제(완전 삭제)
		for(NoteVO vo:list) {
			NoteVO inVO = new NoteVO();
			// 보낸 쪽지함 지우기
			inVO.setNoteNo(vo.getNoteNo());
			inVO.setNoteDiv(3);
			inVO.setEmployeeId(vo.getSenderId());
			delete(inVO);
			// 받은 쪽지함 지우기
			inVO.setEmployeeId(vo.getReceiveId());
			delete(inVO);
		}
		// 등록
		for(NoteVO vo:list) {
			message = service.doInsert(vo);
			assertThat(message.getMsgId(), is("1"));
			LOG.debug("등록 결과 : "+message.getMsgContents());
		}
		
		// 조회
		NoteVO inVO = new NoteVO();
		inVO.setNoteDiv(2);
		inVO.setEmployeeId("admin");
		Search searchVO = new Search("10", "ID03");
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("noteVO", inVO);
		search.put("searchVO", searchVO);
		List<NoteVO> noteList = service.doSelectList(search);
		//assertThat(noteList.size(), is(2));
		
		
//		for(NoteVO vo:list) {
//			NoteVO outVO = service.doSelectOne(vo);
//			assertThat(outVO,is(notNullValue()));
//			checkNote(vo,outVO);
//		}
		
		
	}
	
	public void checkNote(NoteVO orgNote, NoteVO vsNote) {
		assertThat(orgNote.getNoteNo(), is(vsNote.getNoteNo()));
		assertThat(orgNote.getNoteDiv(), is(vsNote.getNoteDiv()));
		assertThat(orgNote.getSenderId(), is(vsNote.getSenderId()));
		assertThat(orgNote.getSenderNm(), is(vsNote.getSenderNm()));
		assertThat(orgNote.getReceiveId(), is(vsNote.getReceiveId()));
		assertThat(orgNote.getReceiveNm(), is(vsNote.getReceiveNm()));
		assertThat(orgNote.getEmployeeId(), is(vsNote.getEmployeeId()));
		assertThat(orgNote.getEmployeeNm(), is(vsNote.getEmployeeNm()));
		assertThat(orgNote.getTitle(), is(vsNote.getTitle()));
		assertThat(orgNote.getContents(), is(vsNote.getContents()));
		
	}
	
	public void delete(NoteVO vo) {
		Message message;
		message = service.doDelete(vo);
		LOG.debug("message : "+message.getMsgId());
	}

	@Test
	@Ignore
	public void bean() {
		LOG.debug("context : "+context);
		LOG.debug("service : "+service);
	}

}
