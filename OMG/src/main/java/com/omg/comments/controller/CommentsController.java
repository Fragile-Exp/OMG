package com.omg.comments.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.cmn.StringUtil;
import com.omg.comments.domain.CommentsVO;
import com.omg.comments.service.CommentsService;

@Controller

public class CommentsController 
{
	final Logger LOG = LoggerFactory.getLogger(CommentsController.class);
	
	@Autowired
	CommentsService commentsService;
	
	@RequestMapping(value="comments/doInsert.do", method = RequestMethod.POST
											 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doInsert(HttpServletRequest req, CommentsVO comments, Model model)
	{
		LOG.debug("===========================");
		LOG.debug("=doInsert()=");
		LOG.debug("=comments : "+comments);
		LOG.debug("===========================");
		
		comments.setContents(StringUtil.nvl(req.getParameter("write_contents"),""));
		comments.setModId(comments.getRegId());
		
		int flag = this.commentsService.doInsert(comments);
		model.addAttribute(comments);
		
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
	@RequestMapping(value="comments/doDelete.do", method = RequestMethod.POST
											 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody	
	public String doDelete(CommentsVO comments)
	{
		LOG.debug("===========================");
		LOG.debug("=doDelete()=");
		LOG.debug("=comments : "+comments);
		LOG.debug("===========================");
		
		int flag = this.commentsService.doDelete(comments);
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
	
	@RequestMapping(value="comments/doUpdate.do", method = RequestMethod.POST
											 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doUpdate(HttpServletRequest req, CommentsVO comments, Model model)
	{
		LOG.debug("===========================");
		LOG.debug("=doUpdate()=");
		LOG.debug("=comments : "+comments);
		LOG.debug("===========================");
		
		
		comments.setContents(StringUtil.nvl(req.getParameter("write_contents"),""));
		comments.setModId(comments.getRegId());
		
		int flag = this.commentsService.doUpdate(comments);
		model.addAttribute(comments);
		
		LOG.debug("===========================");
		LOG.debug("=comments : "+comments);
		LOG.debug("===========================");
		
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
	
	@RequestMapping(value="comments/doSelectOne.do", method = RequestMethod.GET
			   									,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public CommentsVO doSelectOne(CommentsVO comments)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne()=");
		LOG.debug("=comments : "+comments);
		LOG.debug("===========================");
		
		CommentsVO outVO = (CommentsVO) this.commentsService.doSelectOne(comments);

		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		
		LOG.debug("===========================");
		LOG.debug("=json : "+json);
		LOG.debug("===========================");
		
		return outVO;
	}
	@RequestMapping(value="comments/doSelectList.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(CommentsVO comments, Model model)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectList()=");
		LOG.debug("===========================");
		LOG.debug(comments.getBoardSeq()+"");

		
		List<CommentsVO> list = this.commentsService.doSelectList(comments);
		model.addAttribute("list", list);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
}
