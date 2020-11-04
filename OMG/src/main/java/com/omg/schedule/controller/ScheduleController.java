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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omg.schedule.domain.ScheduleVO;
import com.omg.schedule.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {
    Logger log = LoggerFactory.getLogger(ScheduleController.class);
    
    @Autowired
    private ScheduleService scheduleService;
    
    /**
     * 일정 리스트
     * deptNo: 0(전체검색) or 부서별검색
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
    
    /**
     * 일정 추가
     * @param inVO
     * @param rttr
     * @author 박정민
     * @Date 2020-11-04
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(ScheduleVO inVO, RedirectAttributes rttr) {
	log.debug("[Insert]ScheduleVO: " + inVO);
	
	scheduleService.doInsert(inVO);
	
	return "redirect:/schedule/list"; //생성 완료되면 일정관리 페이지로 리다이렉트
    }
    
    //입력 페이지를 보여주는 역할
    @GetMapping("/insert")
    public void register() {}
    
    /**
     * 단건 검색
     * @param scheduleNo
     * @param model
     * @author 박정민
     * @Date 2020-11-04
     */
    @RequestMapping(value = {"/get", "/update"}, method = RequestMethod.GET)
    public void get(@RequestParam("scheduleNo") int scheduleNo, Model model) {
	log.debug("doSelectOne or doUpdate.....");
	
	model.addAttribute("schedule", scheduleService.doSelectOne(scheduleNo));
    }
    
    /**
     * 일정 수정
     * @param inVO
     * @param rttr
     * @author 박정민
     * @Date 2020-11-04
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ScheduleVO inVO, RedirectAttributes rttr) {
	log.debug("[Update]ScheduleVO: " + inVO);
	
	if(scheduleService.doUpdate(inVO) == 1) {
	    rttr.addFlashAttribute("result", "success");
	}
	
	return "redirect:/schedule/list";
    }
    
    /**
     * 일정 삭제
     * @param scheduleNo
     * @param rttr
     * @author 박정민
     * @Date 2020-11-04
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("scheduleNo") int scheduleNo, RedirectAttributes rttr) {
	log.debug("[Delete]scheduleNo: " + scheduleNo);
	
	if(scheduleService.doDelete(scheduleNo) == 1) {
	    rttr.addFlashAttribute("result", "success");
	}
	
	return "redirect:/schedule/list";
    }
    
}
