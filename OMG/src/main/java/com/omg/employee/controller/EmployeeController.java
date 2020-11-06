package com.omg.employee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeService;

@Controller
public class EmployeeController {
	final Logger LOG=LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;
	
	public EmployeeController() {}
	
	
	@RequestMapping(value="employee/doSelectList.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doSelectList(HttpServletRequest req,Search search) {
		LOG.debug("1==================");
        LOG.debug("=search="+search);
        LOG.debug("==================");
        if(search.getPageNum()==0) {
        	search.setPageNum(1);
        }
        
        //페이지 사이즈 기본값 처리 
        if(search.getPageSize()==0) {
        	search.setPageSize(10);
        }
        
        //검색구분
        search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
        
        //검색어
        search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
        LOG.debug("2==================");
        LOG.debug("=null처리 search="+search);
        LOG.debug("==================");       
        
        List<EmployeeVO> list= this.employeeService.doSelectList(search);
        
		Gson gson=new Gson();
        
		String json = gson.toJson(list);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");   		
		
		
		return json;
	}
	
	@RequestMapping(value="employee/doUpdate.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doUpdate(EmployeeVO employee) {
		LOG.debug("1==================");
        LOG.debug("=user="+employee);
        LOG.debug("==================");	
        
        int flag=this.employeeService.doUpdate(employee);
        LOG.debug("2==================");
        LOG.debug("=flag="+flag);
        LOG.debug("==================");     
        
        Message message=new Message();
        message.setMsgId(String.valueOf(flag));
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getName()+" 님이 수정 되었습니다.");
        }else {
        	message.setMsgContents(employee.getName()+" 님 수정 실패.");
        }
        
        Gson   gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");		
		return json;
	}
	
	
	@RequestMapping(value="employee/doSelectOne.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doSelectOne(EmployeeVO employee) {
		LOG.debug("==================");
        LOG.debug("=employee="+employee);
        LOG.debug("==================");
        
        EmployeeVO outVO=employeeService.doSelectOne(employee);
        LOG.debug("==================");
        LOG.debug("=outVO="+outVO);
        LOG.debug("==================");
        
        Gson gson=new Gson();
        String json = gson.toJson(outVO);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
         
        return json;
	}
	
	
	@RequestMapping(value="employee/doDelete.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doDelete (EmployeeVO employee) {
		LOG.debug("==================");
        LOG.debug("=employee="+employee);
        LOG.debug("==================");
        
        int flag=this.employeeService.doDelete(employee);
        LOG.debug("==================");
        LOG.debug("=flag="+flag);
        LOG.debug("=================="); 
        
        //메시지 처리
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getName()+" 님이 삭제 되었습니다.");
        }else {
        	message.setMsgContents(employee.getName()+" 님 삭제 실패.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
		return json;
	}
	
	

	@RequestMapping(value="employee/doInsert.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8"
			)
	@ResponseBody
	public String doInsert(EmployeeVO employee) {
		LOG.debug("==================");
        LOG.debug("=employee="+employee);
        LOG.debug("==================");
        
        int flag=this.employeeService.doInsert(employee);
        LOG.debug("==================");
        LOG.debug("=flag="+flag);
        LOG.debug("=================="); 
        Message  message=new Message();
        message.setMsgId(flag+"");
        
        if(flag ==1 ) {
        	message.setMsgContents(employee.getName()+" 님이 등록 되었습니다.");
        }else {
        	message.setMsgContents(employee.getName()+" 님 등록 실패.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
        
		return json;
	}
}
