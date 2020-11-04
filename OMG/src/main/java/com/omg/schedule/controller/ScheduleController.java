package com.omg.schedule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.omg.schedule.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {
    Logger log = LoggerFactory.getLogger(ScheduleController.class);
    
    @Autowired
    private ScheduleService scheduleService;
    
    /**
     * 일정 리스트
     * @param deptNo
     * @param model
     * @author 박정민
     * @Date 2020-11-03
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(@RequestParam("deptNo") int deptNo, Model model) {
	log.debug("doSelectList.....");
	
	model.addAttribute("list", scheduleService.doSelectList(deptNo));
    }
    
    
}
