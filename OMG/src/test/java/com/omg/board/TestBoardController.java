package com.omg.board;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.omg.board.dao.BoardDaoImpl;
import com.omg.board.domain.BoardVO;
import com.omg.board.service.BoardService;
import com.omg.cmn.Message;
import com.omg.cmn.Search;

//메소드 수행 순서:
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class TestBoardController 
{
   final Logger LOG = LoggerFactory.getLogger(TestBoardController.class);

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardDaoImpl boardDaoImpl;

	List<BoardVO> boards;
	
	// 브라우저 대신 Mock
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception 
	{
		boards =Arrays.asList( new BoardVO(1, "130", "H130_01_제목", "H130_01_내용", 10,"1", "20201112", "SAMGYOBI", "20201112", "SAMGYOBI","")
							  ,new BoardVO(2, "130", "H130_02_제목", "H130_02_내용", 11,"2", "20201112", "SAMGYOBI", "20201112", "SAMGYOBI","")
							  ,new BoardVO(3, "130", "H130_03_제목", "H130_03_내용", 49,"3", "20201112", "SAMGYOBI", "20201112", "SAMGYOBI","")
							  ,new BoardVO(4, "130", "H130_04_제목", "H130_04_내용", 51,"4", "20201112", "SAMGYOBI", "20201112", "SAMGYOBI","")
							  ,new BoardVO(5, "130", "H130_05_제목", "H130_05_내용", 99,"5", "20201112", "SAMGYOBI", "20201112", "SAMGYOBI","")
							);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=mockMvc=" + mockMvc);
		assertThat(mockMvc, is(notNullValue()));
	}

	@Test
	public void doSelectList() throws Exception 
	{
		Search search = new Search("","",10,1);
		search.setDiv("10");
		MockHttpServletRequestBuilder createMessage = 
			MockMvcRequestBuilders.get("/board/doSelectList.do")
								  .param("div", search.getDiv())
								  .param("searchDiv", search.getSearchDiv())
								  .param("searchWord", search.getSearchWord())
								  .param("pageSize", search.getPageSize()+"")
								  .param("pageNum", search.getPageNum()+"");

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());

		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");

	}

	@Test
	@Ignore
	public void doUpdate() throws Exception 
	{
		//1.삭제
		//boardDaoImpl.doDeleteAll();
		
		//2.단건등록
		int flag = boardDaoImpl.doInsert(boards.get(0));
		assertThat(flag, is(1));
		
		//3.단건조회
		//BoardVO outVO = boardDaoImpl.doSelectTitleOne(boards.get(0));
		BoardVO boardVO = new BoardVO();
		
		//3.1.조회데이터 수정
		boardVO.setTitle(boardVO.getTitle()+"_U");
		boardVO.setContents(boardVO.getContents()+"_U");
		boardVO.setModId(boardVO.getModId()+"_U");
		
		MockHttpServletRequestBuilder createMessage = 
			 MockMvcRequestBuilders.post("/board/doUpdate.do")
								   .param("seq", boardVO.getBoardSeq()+"")
								   .param("div", boardVO.getDiv())
								   .param("title", boardVO.getTitle())
								   .param("contents", boardVO.getContents())
								   .param("readCnt", boardVO.getReadCnt()+"")
								   .param("fileCode", boardVO.getFilecode()+"")
								   .param("regId", boardVO.getRegId())
								   .param("modId", boardVO.getModId());
		
		ResultActions resultActions =mockMvc.perform(createMessage)      
		  .andExpect(status().is2xxSuccessful());
		
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		
		//json -> Message
		Gson gson=new Gson();
		
		Message message = gson.fromJson(result, Message.class);
		assertThat(message.getMsgId(),is(1+""));
		LOG.debug("===========================");
		LOG.debug("=message=" + message);
		LOG.debug("===========================");
		
		//4.수정데이터 조회
		BoardVO vsVO = boardDaoImpl.doSelectOne(boardVO);
		
		//5.비교
		checkSameData(vsVO,boardVO);
		
	}

	public void checkSameData(BoardVO vsVO, BoardVO boardVO) 
	{
		assertThat(vsVO.getBoardSeq(), is(boardVO.getBoardSeq()));
		assertThat(vsVO.getTitle(), is(boardVO.getTitle()));
		assertThat(vsVO.getContents(), is(boardVO.getContents()));
		assertThat(vsVO.getModId(), is(boardVO.getModId()));
	}

	@Test
	@Ignore
	public void doInsert() throws Exception 
	{
		BoardVO boardVO = boards.get(0);
		// url,param set /board/doInsert.do
		MockHttpServletRequestBuilder createMessage = 
			 MockMvcRequestBuilders.post("/board/doInsert.do")
								   .param("div", boardVO.getDiv())
								   .param("title", boardVO.getTitle())
								   .param("contents", boardVO.getContents())
								   .param("readCnt", boardVO.getReadCnt()+"")
								   .param("fileCode", boardVO.getFilecode()+"")
								   .param("regId", boardVO.getRegId())
								   .param("modId", boardVO.getModId());

		ResultActions resultActions =mockMvc.perform(createMessage)      
		.andExpect(status().is2xxSuccessful());
		//.andExpect(jsonPath("$.msgContents", is("등록 되었습니다.")))
		//.andExpect(jsonPath("$.msgId", is("1")));
		
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		
		//json -> Message
		Gson gson=new Gson();
		
		Message message = gson.fromJson(result, Message.class);
		LOG.debug("===========================");
		LOG.debug("=message=" + message);
		LOG.debug("===========================");   
		
	}
	
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	//@Ignore
	public void beans() 
	{
		LOG.debug("---------------------------");
		LOG.debug("webApplicationContext:" + webApplicationContext);
		LOG.debug("boardService:" + boardService);
		LOG.debug("---------------------------");

		assertThat(webApplicationContext, is(notNullValue()));
		String codeList = "PAGE_SIZE,BOARD_CONDITION";
		List<String> list = makeForeach(codeList,",");
		
		LOG.debug(list.toString());
	}

	public List<String> makeForeach(String codeList, String gb){
		if(null==codeList || "".equals(codeList)) 
		{
			return null;
		}

		List<String> cdList = new ArrayList<String>();
		String[] aCode = codeList.split(gb);
		for(int i=0;i<aCode.length;i++) 
		{
			cdList.add(aCode[i].toString());
		}
		
		return cdList;

	}

}