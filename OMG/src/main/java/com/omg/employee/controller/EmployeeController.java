package com.omg.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	final Logger LOG=LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	public EmployeeController() {}

	@RequestMapping(value="user/doInsert.do",method = RequestMethod.POST
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
