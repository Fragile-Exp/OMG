package com.omg.document.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.document.domain.DocumentVO;
import com.omg.document.service.DocumentService;




@Controller
public class DocumentController {
	final Logger  LOG = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	DocumentService documentService; 
	
	@Autowired
	MessageSource messageSource;
	
	String url = "http://localhost:8080/cmn";
	//--view--
	
	//--?ì±Î°? Î¨∏ÏÑú Î™©Î°ù page
	@RequestMapping(value="document/document.do", method = RequestMethod.GET )
	public String document_view(DocumentVO documentVO, Model  model){
		
		
		documentVO.setEmployeeId("ID01");
		
		int flag = documentService.doempIdcheck(documentVO);
		model.addAttribute("flag", flag);
		LOG.debug("=doempIdcheck="+flag);
		
		
		
		if(flag == 0) {
			LOG.debug("Îì±Î°ùÎêòÏñ¥ÏßÑ Î¨∏ÏÑúÍ∞Ä ÏóÜÏäµÎãàÎã§.");
		}else{
			List<DocumentVO> IdSeleteList = documentService.doempIdSelectList(documentVO);
			model.addAttribute("IdSeleteList", IdSeleteList);
			model.addAttribute("IdSeleteSize", IdSeleteList.size());
			
			LOG.debug("=IdSeleteList="+IdSeleteList);
			LOG.debug("=IdSeleteSize="+IdSeleteList.size());
		}
		
		LOG.debug("Ï¢ÖÎ£å");
		return  "document/document";
	}
	
	//--Î¨∏ÏÑú ?ì±Î°? page
	@RequestMapping(value="document/document_reg.do", method = RequestMethod.GET )
	public String document_reg(){
		LOG.debug("===========================");
		LOG.debug("=document_reg.do=");
		LOG.debug("===========================");
		return "document/document_reg";
	}
	
	//--Î¨∏ÏÑú ?ÉÅ?Ñ∏ ?†ïÎ≥? page
	@RequestMapping(value="document/document_info.do", method = RequestMethod.GET )
	public String document_info(DocumentVO documentVO, Model  model){
		
		
		//String Id = "ID02";
		documentVO.setDocumentId("E_0001");
		documentVO.setEmployeeId("ID02");
		
		
		
		List<DocumentVO> one = documentService.doempIdSelectList(documentVO);
	
		DocumentVO voList = one.get(0);
		
		model.addAttribute("one", voList);
		model.addAttribute("title", (voList.getTitle()));
		model.addAttribute("kind", (voList.getKind()));
		model.addAttribute("dDay", (voList.getdDay()));
		model.addAttribute("okUser", (voList.getOkUser()));
		model.addAttribute("cont", (voList.getDocumentCont()));
		
		
		// .get -> 0 ?ù¥Î©? if(){ .set}
		// .set(pkÍ∞?)(StringUtil.nvl( .get(pkÍ∞?),""))
		//
		//model.addAttribute("",VO)
		//?ÑúÎπÑÏä§ ?ò∏Ï∂? 
		//List<VO> document = this.Service.doSeleteOne(VO);
		//Í∞? ?Ñ§?†ï 
		//model.addAttribute("nameÎ™?", ?ç∞?ù¥?Ñ∞  ) <?ôîÎ©¥ÏúºÎ°? ?ç∞?Öê?ù¥?Ñ∞ Î≥¥ÎÇ¥Í∏?> 
		
		//jsp file ?óê?Ñú?äî ${model nameÎ™? ?ûÖ?†• }
		
		String view = "document/document_info";
		
		return view;
	}
	
	
	
	//--Í∏∞Îä•----------------------------------------------------------------------------- 
	
	//--?ã®Í±? Í≤??Éâ
	@RequestMapping(value="document/doSelectOne.do",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doSelectOne(DocumentVO documentVO) {
        LOG.debug("==================");
        LOG.debug("=documentVO="+documentVO);
        LOG.debug("==================");		
        
        //documentVO.setDocumentId("E_0001");
        DocumentVO outVO =documentService.doSelectOne(documentVO);
        
        LOG.debug("==================");
        LOG.debug("=doSelectOne="+outVO);
        LOG.debug("==================");
        
        Gson gson=new Gson();
        String json = gson.toJson(outVO);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
         
        return json;
	}
	//-- ?†ÑÏ≤? Í≤??Éâ
	@RequestMapping(value="document/doSelectList.do",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doSelectList(DocumentVO documentVO) {
        LOG.debug("==================");
        LOG.debug("=documentVO="+documentVO);
        LOG.debug("==================");		
        
        //documentVO.setDocumentId("E_0001");
        List<DocumentVO> outVO =documentService.doSelectList();
        
        LOG.debug("==================");
        LOG.debug("=doSelectList="+outVO);
        LOG.debug("==================");
        
        Gson gson=new Gson();
        String json = gson.toJson(outVO);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
         
        return json;
	}
	//-- ?Ç¨Î≤? Í∏∞Ï? Í≤??Éâ
	@RequestMapping(value="document/doempIdSelectList.do",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doempIdSelectList(DocumentVO documentVO) {
        LOG.debug("==================");
        LOG.debug("=documentVO="+documentVO);
        LOG.debug("==================");		
        
        //documentVO.setDocumentId("E_0001");
        List<DocumentVO> outVO =documentService.doempIdSelectList(documentVO);
        
        LOG.debug("==================");
        LOG.debug("=doempIdSelectList="+outVO);
        LOG.debug("==================");
        
        Gson gson=new Gson();
        String json = gson.toJson(outVO);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
         
        return json;
	}
	
	
	
	//-- ?ì±Î°?
	@RequestMapping(value="document/doInsert.do",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doInsert(DocumentVO documentVO) {
        LOG.debug("==================");
        LOG.debug("=documentVO="+documentVO);
        LOG.debug("==================");		
        
        //documentVO.setDocumentId("E_0001");
        int flag =documentService.doInsert(documentVO);
        
        LOG.debug("=doInsert="+flag);
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(documentVO.getTitle()+"Î¨∏ÏÑúÍ∞? ?ì±Î°ùÎêò?óà?äµ?ãà?ã§.");
        }else {
        	message.setMsgContents(documentVO.getTitle()+"Î¨∏ÏÑúÍ∞? ?ì±Î°ùÏã§?å®?êò?óà?äµ?ãà?ã§.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
        return json;
	}
	
	//-- ?Ç≠?†ú
	@RequestMapping(value="document/doDelete.do",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doDelete(DocumentVO documentVO) {
        LOG.debug("==================");
        LOG.debug("=documentVO="+documentVO);
        LOG.debug("==================");		
        
        int flag =documentService.doDelete(documentVO);
        
        LOG.debug("=doDelete="+flag);
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(documentVO.getTitle()+"Î¨∏ÏÑúÍ∞? ?Ç≠?†ú?êò?óà?äµ?ãà?ã§.");
        }else {
        	message.setMsgContents(documentVO.getTitle()+"Î¨∏ÏÑúÍ∞? ?Ç≠?†ú ?ã§?å®?êò?óà?äµ?ãà?ã§.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
        return json;
	}
	
	//-- ?àò?†ï 
	@RequestMapping(value="document/doUpdate.do",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String doUpdate(DocumentVO documentVO) {
        LOG.debug("==================");
        LOG.debug("=documentVO="+documentVO);
        LOG.debug("==================");		
        
        //documentVO.setDocumentId("E_0001");
        int flag =documentService.doUpdate(documentVO);
        
        LOG.debug("=doInsert="+flag);
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(documentVO.getTitle()+"Î¨∏ÏÑúÍ∞? ?àò?†ï?êò?óà?äµ?ãà?ã§.");
        }else {
        	message.setMsgContents(documentVO.getTitle()+"Î¨∏ÏÑúÍ∞? ?àò?†ï ?ã§?å®?êò?óà?äµ?ãà?ã§.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
        return json;
	}



	
}
	

