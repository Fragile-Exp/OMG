package com.omg.attachment;

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

import com.omg.attachment.dao.AttachmentDaoImpl;
import com.omg.attachment.domain.AttachmentVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestAttachmentDao 
{
	
	final Logger LOG = LoggerFactory.getLogger(TestAttachmentDao.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	AttachmentDaoImpl attachmentDao;
	
	AttachmentVO amVO;
	
	List<AttachmentVO> list;
	
	@Before
	public void setUp() throws Exception 
	{
		LOG.debug("=============================");
		LOG.debug("=setUp()=");
		LOG.debug("=============================");
		list = Arrays.asList(
							 new AttachmentVO("61",1,"attachment/test/","test01.img","test01origin.img"),
							 new AttachmentVO("1",1,"attachment/test/","test02.img","test02origin.img"),
							 new AttachmentVO("34",1,"attachment/test/","test03.img","test03origin.img"),
							 new AttachmentVO("38",1,"attachment/test/","test04.img","test04origin.img"),
							 new AttachmentVO("37",1,"attachment/test/","test05.img","test05origin.img")
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
		for(AttachmentVO vo : list)
		{
			LOG.debug("test()[doInsert]");
			flag = attachmentDao.doInsert(vo);
			assertThat(flag, is(1));
		}
		
//		//삭제
//		for(AttachmentVO vo : list)
//		{
//			LOG.debug("test()[doDelete]");
//			assertThat(flag, is(1));
//		}
//		
//		//단건삭제
//		LOG.debug("test()[doDeleteOne]");
//		
//		AttachmentVO paramDeleteOne = list.get(0);
//		LOG.debug("paramDeleteOne : "+paramDeleteOne);
//		
//		flag = attachmentDao.doDeleteOne(paramDeleteOne);
//		assertThat(flag, is(1));
//		
//		
//		//목록조회
//		LOG.debug("test()[doSelectList]");
//		List<AttachmentVO> AmList = attachmentDao.doSelectList(list.get(0));
//		LOG.debug("AmList.size() : "+AmList.size());
//		
//		//단건조회
//		LOG.debug("test()[doSelectOne]");
//		
//		AttachmentVO paramSelectOne = list.get(0);
//		LOG.debug("paramSelectOne : "+paramSelectOne);
//		
//		AttachmentVO oneAttachment = attachmentDao.doSelectOne(paramSelectOne);
//		LOG.debug("oneAttachment : "+oneAttachment);
		
	}

}
