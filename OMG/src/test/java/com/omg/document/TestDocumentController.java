package com.omg.document;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.omg.document.controller.DocumentController;
import com.omg.document.domain.DocumentVO;
import com.omg.document.service.DocumentService;
import com.omg.cmn.Message;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 수행 순서
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 Junit 기능 확장 applicationContext 공유
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestDocumentController {

	final Logger LOG = LoggerFactory.getLogger(TestDocumentService.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	DocumentController documentController;
	
	
	MockMvc mockMvc;
	
	
	List<DocumentVO> list;
	
//	@Before
//	public void setUp() throws Exception {
//		LOG.debug(" === setUp === ");
//		list = Arrays.asList(
//				new DocumentVO("W_0001","ID01",1,"워드01","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//				new DocumentVO("E_0002","ID01",1,"엑셀01","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//				new DocumentVO("E_0003","ID01",1,"엑셀02","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//				new DocumentVO("E_0004","ID01",1,"엑셀03","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//				new DocumentVO("E_0005","ID01",1,"엑셀04","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//				new DocumentVO("E_0006","ID01",1,"엑셀05","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//				new DocumentVO("P_0003","ID01",1,"ppt01","2020-11-19","문서설명내용",0,"ID99(관리자)"),
//				new DocumentVO("E_0001","ID02",2,"엑셀01","2020-11-19","문서설명내용",1,"ID99(관리자)"),
//				new DocumentVO("P_0001","ID03",3,"ppt01","2020-11-19","문서설명내용",2,"ID99(관리자)")
//				);   
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	

	@After
	public void tearDown() throws Exception {
		LOG.debug("=====================");
		LOG.debug("==tearDown==");
		LOG.debug("=====================");
	
	}
	
	
	
	@Test
	public void TestController() throws Exception {
		for(DocumentVO vo :list) {
			doDelete(vo);
		}
	
		for(DocumentVO vo :list) {
			int flag = doInsert(vo);
		}
		
		DocumentVO param = list.get(0);
		param.setEmployeeId(param.getEmployeeId()+"_U");
		param.setTitle(param.getTitle()+"_U");
	    param.setDocumentCont(param.getDocumentCont()+"_U");
		doUpdate(param);
		
		doSeleteOne(param);
		
		
		doSeleteList(param);
		
		param.setEmployeeId("ID01");
		
		doempIdSeleteList(param);
		
		
		
	}

	
	
	
	//--기능----------------------------------------------------------
	
	public void doempIdSeleteList(DocumentVO documentVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/document/doempIdSelectList.do")
				.param("employeeId", documentVO.getEmployeeId());
	
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
				
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=doempIdSeleteList=" + result);
		LOG.debug("===========================");
	}
	
	
	public int doInsert(DocumentVO documentVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/document/doInsert.do")
				.param("documentId", documentVO.getDocumentId())
				.param("employeeId", documentVO.getEmployeeId())
				.param("kind", documentVO.getKind()+"")
				.param("title", documentVO.getTitle())
				.param("dDay", documentVO.getdDay())
				.param("documentCont", documentVO.getDocumentCont())
				.param("documentSet", documentVO.getDocumentSet()+"")
				.param("okUser", documentVO.getOkUser());

	
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
				
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		//json -> Message
		Gson gson=new Gson();
		Message outVO = gson.fromJson(result, Message.class);
		LOG.debug("===========================");
		LOG.debug("=Message=" + outVO);
		LOG.debug("===========================");	
		
		return Integer.valueOf(outVO.getMsgId());
		
	}
	public int doUpdate(DocumentVO documentVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/document/doUpdate.do")
				.param("documentId", documentVO.getDocumentId())
				.param("employeeId", documentVO.getEmployeeId())
				.param("kind", documentVO.getKind()+"")
				.param("title", documentVO.getTitle())
				.param("dDay", documentVO.getdDay())
				.param("documentCont", documentVO.getDocumentCont())
				.param("documentSet", documentVO.getDocumentSet()+"")
				.param("okUser", documentVO.getOkUser());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
				
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();		
		LOG.debug("=doUpdate="+result);
		
		Gson gson=new Gson();
		Message outVO = gson.fromJson(result, Message.class);
		LOG.debug("===========================");
		LOG.debug("=Message=" + outVO);
		LOG.debug("===========================");	
		
		return Integer.valueOf(outVO.getMsgId());
				
	}
	public void doDelete(DocumentVO documentVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/document/doDelete.do")
				.param("documentId", documentVO.getDocumentId());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=============================");
		LOG.debug("=doDelete="+result);
		LOG.debug("=============================");
	}
	public void doSeleteOne(DocumentVO documentVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/document/doSelectOne.do")
				.param("documentId", documentVO.getDocumentId());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
				
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=============================");
		LOG.debug("=doSeleteOne="+result);
		LOG.debug("=============================");
		
		
	}
	public void doSeleteList(DocumentVO documentVO) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/document/doSelectList.do");
				
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
				
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("=============================");
		LOG.debug("=doSeleteList="+result);
		LOG.debug("=============================");
		
	}
	


}
