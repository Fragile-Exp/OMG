package com.omg.comments.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.comments.domain.CommentsVO;
import com.omg.comments.service.CommentsService;

@Controller
public class CommentsController 
{
	final Logger LOG = LoggerFactory.getLogger(CommentsController.class);
	
	@Autowired
	CommentsService commentsService;
	
	@RequestMapping(value="Comments/doInsert.do", method = RequestMethod.POST
											 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doInsert(CommentsVO Comments)
	{
		LOG.debug("===========================");
		LOG.debug("=doInsert()=");
		LOG.debug("=Comments : "+Comments);
		LOG.debug("===========================");
		
		int flag = this.commentsService.doInsert(Comments);
		LOG.debug("===========================");
		LOG.debug("=flag : "+flag);
		LOG.debug("===========================");
		
		//메세지 처리
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag==1)
		{
			message.setMsgContents(" 댓글 등록이 완료 되었습니다.");
		}
		else
		{
			message.setMsgContents(" 댓글 등록에 실패 하였습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
	@RequestMapping(value="Comments/doDelete.do", method = RequestMethod.POST
											 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody	
	public String doDelete(CommentsVO Comments)
	{
		LOG.debug("===========================");
		LOG.debug("=doDelete()=");
		LOG.debug("=Comments : "+Comments);
		LOG.debug("===========================");
		
		int flag = this.commentsService.doDelete(Comments);
		LOG.debug("===========================");
		LOG.debug("=flag : "+flag);
		LOG.debug("===========================");
		
		//메세지 처리
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag==1)
		{
			message.setMsgContents(" 댓글 삭제가 완료 되었습니다.");
		}
		else
		{
			message.setMsgContents(" 댓글 삭제에 실패 하였습니다.");
		}
		
		Gson gson = new Gson();
        String json = gson.toJson(message);
        LOG.debug("===========================");
        LOG.debug("=json="+json);
        LOG.debug("===========================");
		
		return json;
	}
	
	@RequestMapping(value="Comments/doUpdate.do", method = RequestMethod.POST
											 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doUpdate(CommentsVO Comments)
	{
		LOG.debug("===========================");
		LOG.debug("=doUpdate()=");
		LOG.debug("=board : "+Comments);
		LOG.debug("===========================");
		
		int flag = this.commentsService.doUpdate(Comments);
		LOG.debug("===========================");
		LOG.debug("=flag : "+flag);
		LOG.debug("===========================");
		
		Message message = new Message();
		message.setMsgId(String.valueOf(flag));
		
		if(flag==1)
		{
			message.setMsgContents(" 댓글 수정이 완료 되었습니다.");
		}
		else
		{
			message.setMsgContents(" 댓글 수정에 실패 하였습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
	
	@RequestMapping(value="Comments/doSelectOne.do", method = RequestMethod.GET
			   									,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public CommentsVO doSelectOne(CommentsVO Comments)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne()=");
		LOG.debug("=Comments : "+Comments);
		LOG.debug("===========================");
		
		CommentsVO outVO = (CommentsVO) this.commentsService.doSelectOne(Comments);

		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		
		LOG.debug("===========================");
		LOG.debug("=json : "+json);
		LOG.debug("===========================");
		
		return outVO;
	}
	@RequestMapping(value="Comments/doSelectList.do", method = RequestMethod.GET
												 , produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doSelectList(Search search)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne()=");
		LOG.debug("=search : "+search);
		LOG.debug("===========================");
		
		//페이지 num 기본값 처리
		if(search.getPageNum()==0)
		{
			search.setPageNum(1);;
		}
		
		//페이지 사이즈 기본값 처리
		if(search.getPageSize()==0)
		{
			search.setPageSize(10);
		}
		
		//검색구분
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
		
		//검색어
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		
		LOG.debug("===========================");
		LOG.debug("=null 처리 이후 search : "+search);
		LOG.debug("===========================");
		
		List<CommentsVO> list = this.commentsService.doSelectList(search);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
}
