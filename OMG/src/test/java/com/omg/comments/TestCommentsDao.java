package com.omg.comments;

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

import com.omg.cmn.Search;
import com.omg.comments.dao.CommentsDaoImpl;
import com.omg.comments.domain.CommentsVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestCommentsDao 
{
	final Logger LOG = LoggerFactory.getLogger(TestCommentsDao.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	CommentsDaoImpl commentsDao;
	
	CommentsVO coVO;
	
	List<CommentsVO> list;

	@Before
	public void setUp() throws Exception 
	{
		LOG.debug("=============================");
		LOG.debug("=setUp()=");
		LOG.debug("=============================");
		list = Arrays.asList(
				 new CommentsVO(26,1,0,"1번게시글의댓글","","SAMGYOBI","","SAMGYOBI"),
				 new CommentsVO(27,1,1,"1번게시글의댓글의대댓글","","SAMGYOBI","","SAMGYOBI"),
				 new CommentsVO(28,2,0,"2번게시글의댓글","","SAMGYOBI","","SAMGYOBI"),
				 new CommentsVO(29,2,3,"2번게시글의댓글의대댓글","","SAMGYOBI","","SAMGYOBI")
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
		
//		//등록
//		for(CommentsVO vo : list)
//		{
//			LOG.debug("test()[doInsert]");
//			flag = commentsDao.doInsert(vo);
//			assertThat(flag, is(1));
//		}
//		
//		//삭제
//		for(CommentsVO vo : list)
//		{
//			LOG.debug("test()[doDelete]");
//			flag = commentsDao.doDelete(vo);
//			assertThat(flag, is(1));
//		}
		
		//수정
		LOG.debug("test()[doUpdate]");
		
		CommentsVO param01 = list.get(0);
		CommentsVO param02 = list.get(1);
		CommentsVO param03 = list.get(2);
		CommentsVO param04 = list.get(3);
		
		param01.setContents(param01.getContents()+"_U");
		param02.setContents(param02.getContents()+"_U");
		param03.setContents(param03.getContents()+"_U");
		param04.setContents(param04.getContents()+"_U");
		
		for(CommentsVO vo : list)
		{
			flag = commentsDao.doUpdate(vo);
			assertThat(flag, is(1));
		}
//		
//		//목록조회
//		LOG.debug("test()[doSelectList]");
//		List<CommentsVO> coList = commentsDao.doSelectList(list.get(0));
//		LOG.debug("coList.size() : "+coList.size());
		
//		//단건조회
//		LOG.debug("test()[doSelectOne]");
//		
//		CommentsVO param = list.get(0);
//		LOG.debug("param : "+param);
//		
//		CommentsVO oneComments = commentsDao.doSelectOne(param);
//		LOG.debug("oneComments : "+oneComments);
	}

}
