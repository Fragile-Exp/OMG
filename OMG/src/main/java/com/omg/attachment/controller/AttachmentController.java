package com.omg.attachment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.omg.attachment.domain.AttachmentVO;
import com.omg.attachment.service.AttachmentServiceImpl;
import com.omg.cmn.Message;

@Controller
public class AttachmentController 
{
	final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);
	
	@Autowired
	AttachmentServiceImpl attachmentServiceImpl;
	
	@RequestMapping(value="attachment/doInsert.do", method = RequestMethod.POST
											 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doInsert(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doInsert()=");
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		int flag = this.attachmentServiceImpl.doInsert(attachment);
		LOG.debug("===========================");
		LOG.debug("=flag : "+flag);
		LOG.debug("===========================");
		
		//메세지 처리
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag==1)
		{
			message.setMsgContents(" 파일 첨부가 완료 되었습니다.");
		}
		else
		{
			message.setMsgContents(" 파일 첨부에 실패 하였습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
	
	@RequestMapping(value="attachment/doDelete.do", method = RequestMethod.POST
			 									  ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doDelete(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doDelete()=");
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		int flag = this.attachmentServiceImpl.doDelete(attachment);
		LOG.debug("===========================");
		LOG.debug("=flag : "+flag);
		LOG.debug("===========================");
		
		//메세지 처리
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag==1)
		{
			message.setMsgContents(" 첨부된 모든 파일 삭제가 완료 되었습니다.");
		}
		else
		{
			message.setMsgContents(" 첨부된 모든 파일 삭제에 실패 하였습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
	
	@RequestMapping(value="attachment/doDeleteOne.do", method = RequestMethod.POST
													 ,produces = "application/json;charset=UTF-8"
					)
	@ResponseBody
	public String doDeleteOne(AttachmentVO attachment)
	{
		LOG.debug("===========================");
		LOG.debug("=doDeleteOne()=");
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		int flag = this.attachmentServiceImpl.doDeleteOne(attachment);
		LOG.debug("===========================");
		LOG.debug("=flag : "+flag);
		LOG.debug("===========================");
		
		//메세지 처리
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag==1)
		{
			message.setMsgContents(" 첨부된 파일 삭제가 완료 되었습니다.");
		}
		else
		{
			message.setMsgContents(" 첨부된 파일 삭제에 실패 하였습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
	
	@RequestMapping(value="attachment/doSelectOne.do", method = RequestMethod.GET)
	public String doSelectOne(AttachmentVO attachment, Model model)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne()=");
		LOG.debug("=attachment : "+attachment);
		LOG.debug("===========================");
		
		if(null == attachment.getFileCode()) {
			throw new IllegalArgumentException("게시글 seq를 확인하세요");
		}
		
		AttachmentVO outVO = (AttachmentVO) this.attachmentServiceImpl.doSelectOne(attachment);

		model.addAttribute("vo", outVO);
		
		String returnUrl = "board/board_mng";
		
		return returnUrl;
	}
	
	@RequestMapping(value="attachment/doSelectList.do", method = RequestMethod.GET
													  ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(AttachmentVO attachment,Model model)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectList()=");
		LOG.debug("===========================");
		LOG.debug(attachment.getFileCode()+"");

		
		List<AttachmentVO> list = this.attachmentServiceImpl.doSelectList(attachment);
		model.addAttribute("list", list);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		
		return json;
	}
	
}
