package com.omg.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
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

import com.omg.board.dao.BoardDaoImpl;
import com.omg.board.domain.BoardVO;
import com.omg.cmn.Search;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestBoardDao 
{
	
	final Logger LOG = LoggerFactory.getLogger(TestBoardDao.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	BoardDaoImpl boardDao;
	
	BoardVO boVO;
	
	List<BoardVO> list;
	
	@Before
	public void setUp() throws Exception 
	{
		LOG.debug("=============================");
		LOG.debug("=setUp()=");
		LOG.debug("=============================");
		list = Arrays.asList(
							 new BoardVO(18,"10","제목테스트1","내용테스트1",10,"1","","SAMGYOBI","","SAMGYOBI"),
							 new BoardVO(19,"10","제목테스트2","내용테스트2",11,"2","","SAMGYOBI","","SAMGYOBI"),
							 new BoardVO(20,"10","제목테스트3","내용테스트3",49,"3","","SAMGYOBI","","SAMGYOBI"),
							 new BoardVO(21,"10","제목테스트4","내용테스트4",51,"4","","SAMGYOBI","","SAMGYOBI"),
							 new BoardVO(22,"10","제목테스트5","내용테스트5",99,"5","","SAMGYOBI","","SAMGYOBI")
							);
				   
	}
	
	@After
	public void tearDown() throws Exception 
	{
		LOG.debug("=============================");
		LOG.debug("=tearDown()=");
		LOG.debug("=============================");
	}

	@Test
	public void test() throws Exception
	{
		LOG.debug("=============================");
		LOG.debug("=test()=");
		LOG.debug("=============================");
		
		
		int flag = 0;
		
		//등록
		for(BoardVO vo : list)
		{
			LOG.debug("test()[doInsert]");
			flag = boardDao.doInsert(vo);
			assertThat(flag, is(1));
		}
		
//		//삭제
//		for(BoardVO vo : list)
//		{
//			LOG.debug("test()[doDelete]");
//			flag = boardDao.doDelete(vo);
//			assertThat(flag, is(1));
//		}
		
//		//수정
//		LOG.debug("test()[doUpdate]");
//		
//		BoardVO param01 = list.get(0);
//		BoardVO param02 = list.get(1);
//		BoardVO param03 = list.get(2);
//		BoardVO param04 = list.get(3);
//		BoardVO param05 = list.get(4);
//		
//		param01.setTitle(param01.getTitle()+"_U");
//		param02.setTitle(param02.getTitle()+"_U");
//		param03.setTitle(param03.getTitle()+"_U");
//		param04.setTitle(param04.getTitle()+"_U");
//		param05.setTitle(param05.getTitle()+"_U");
//		
//		for(BoardVO vo : list)
//		{
//			flag = boardDao.doUpdate(vo);
//			assertThat(flag, is(1));
//		}
//		
		//목록조회
		LOG.debug("test()[doSelectList]");
		Search search = new Search("", "", 10, 1);
		List<BoardVO> BoList = boardDao.doSelectList(search);
		LOG.debug("list.size() : "+list.size());
//		
//		//단건조회
//		LOG.debug("test()[doSelectOne]");
//		
//		BoardVO param = list.get(0);
//		LOG.debug("param : "+param);
//		
//		BoardVO oneBoard = boardDao.doSelectOne(param);
//		LOG.debug("oneBoard : "+oneBoard);
		
	}

}
