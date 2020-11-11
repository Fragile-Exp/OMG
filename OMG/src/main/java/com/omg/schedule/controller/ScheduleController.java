package com.omg.schedule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omg.cmn.Criteria;
import com.omg.cmn.PageDTO;
import com.omg.schedule.domain.ScheduleVO;
import com.omg.schedule.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {

    Logger log = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService service;

    /**
     * ?ùº?†ï Ï∂îÍ?
     * 
     * @param inVO
     * @param rttr
     * @author Î∞ïÏ†ïÎØ?
     */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String insert(ScheduleVO inVO, RedirectAttributes rttr) {
	log.debug("[Insert]ScheduleVO: " + inVO);

	service.doInsert(inVO);

	return "redirect:/schedule/list.do"; // ?Éù?Ñ± ?ôÑÎ£åÎêòÎ©? ?ùº?†ïÍ¥?Î¶? ?éò?ù¥Ïß?Î°? Î¶¨Îã§?ù¥?†â?ä∏
    }
    
    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public void insert() {
    	
    }
    
    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
    public void insert() {
    	
    }

    /**
     * ?ùº?†ï ?Ç≠?†ú
     * 
     * @param scheduleNo
     * @param rttr
     * @author Î∞ïÏ†ïÎØ?
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public String remove(@RequestParam("scheduleNo") int scheduleNo, RedirectAttributes rttr) {
	log.debug("[Delete]scheduleNo: " + scheduleNo);

	ScheduleVO inVO = new ScheduleVO();
	inVO.setScheduleNo(scheduleNo);

	if (service.doDelete(inVO) == 1) {
	    rttr.addFlashAttribute("result", "success");
	}

	return "redirect:/schedule/list.do";
    }

    /**
     * ?ùº?†ï ?àò?†ï
     * 
     * @param inVO
     * @param rttr
     * @author Î∞ïÏ†ïÎØ?
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public String update(ScheduleVO inVO, RedirectAttributes rttr) {
	log.debug("[Update]ScheduleVO: " + inVO);

	if (service.doUpdate(inVO) == 1) {
	    rttr.addFlashAttribute("result", "success");
	}

	return "redirect:/schedule/list.do";
    }

    /**
     * ?ùº?†ï ?Ñ†?Éù
     * 
     * @param scheduleNo
     * @param model
     * @author Î∞ïÏ†ïÎØ?
     */
    @RequestMapping(value = { "/get.do", "/update.do" }, method = RequestMethod.GET)
    public void get(@RequestParam("scheduleNo") int scheduleNo, Model model) {
	log.debug("doSelectOne or doUpdate.....");

	ScheduleVO inVO = new ScheduleVO();
	inVO.setScheduleNo(scheduleNo);

	model.addAttribute("schedule", service.doSelectOne(inVO));
    }

    /**
     * ?ùº?†ï Í≤??Éâ 
     * deptNo: 0(?†ÑÏ≤¥Í??Éâ) or Î∂??ÑúÎ≥ÑÍ??Éâ
     * 
     * @param deptNo
     * @param model
     * @author Î∞ïÏ†ïÎØ?
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public void list(Criteria cri, Model model) {
	log.debug("doSelectList: " + cri);
	
	model.addAttribute("list", service.doSelectList(cri));
	
	int total = service.getTotalCount(cri);

	model.addAttribute("pageMaker", new PageDTO(cri, total));
    }
}
