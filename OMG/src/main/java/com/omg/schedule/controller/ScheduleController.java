package com.omg.schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.omg.employee.domain.EmployeeVO;
import com.omg.organization.service.DeptService;
import com.omg.schedule.domain.ScheduleVO;
import com.omg.schedule.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {

	Logger log = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private ScheduleService service;

	@Autowired
	private DeptService deptService;

	/**
	 * 일정 추가
	 * 
	 * @param inVO
	 * @param rttr
	 * @param category_id
	 * @return String
	 * @author 박정민
	 */
	@RequestMapping(value = "/doInsert.do", method = RequestMethod.POST)
	public String doInsert(ScheduleVO inVO, RedirectAttributes rttr, @RequestParam("category_id") int category_id) {

		log.debug("[Insert]ScheduleVO: " + inVO);
		int flag = service.doInsert(inVO);

		rttr.addFlashAttribute("result", flag);

		return "redirect:/schedule/doSelectList.do?category_id=" + category_id; // 생성 완료되면 일정관리 페이지로 리다이렉트
	}

	@RequestMapping(value = "/doInsert.do", method = RequestMethod.GET)
	public void insert(@RequestParam("category_id") int category_id, HttpServletRequest req, Model model) {

		//일정 추가시 현재 회원정보를 받아온다.
		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");

		Criteria cri = new Criteria();
		cri.setEmployee_id(sessionVO.getEmployee_id());
		cri.setDept_no(sessionVO.getDept_no());
		cri.setCategory_id(category_id);

		model.addAttribute("cri", cri);
	}

	/**
	 * 일정 삭제
	 * 
	 * @param schedule_no
	 * @param rttr
	 * @param category_id
	 * @return String
	 * @author 박정민
	 */
	@RequestMapping(value = "/doDelete.do", method = RequestMethod.POST)
	public String doDelete(@RequestParam("schedule_no") int schedule_no, RedirectAttributes rttr,
			@RequestParam("category_id") int category_id) {

		log.debug("[Delete]scheduleNo: " + schedule_no);

		ScheduleVO inVO = new ScheduleVO();
		inVO.setSchedule_no(schedule_no);

		if (service.doDelete(inVO) == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/schedule/doSelectList.do?category_id=" + category_id;
	}

	/**
	 * 일정 수정
	 * 
	 * @param inVO
	 * @param rttr
	 * @param category_id
	 * @return String
	 * @author 박정민
	 */
	@RequestMapping(value = "/doUpdate.do", method = { RequestMethod.POST })
	public String doUpdate(ScheduleVO inVO, RedirectAttributes rttr, @RequestParam("category_id") int category_id) {

		log.debug("doUpdate loading.....");
		log.debug("[Update]ScheduleVO: " + inVO);

		if (service.doUpdate(inVO) == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/schedule/doSelectList.do?category_id=" + category_id;
	}

	/**
	 * 일정 선택
	 * 
	 * @param schedule_no
	 * @param cri
	 * @param req
	 * @param model
	 * @author 박정민
	 */
	@RequestMapping(value = { "/doSelectOne.do", "/doUpdate.do" }, method = RequestMethod.GET)
	public void doSelectOne(@RequestParam("schedule_no") int schedule_no, Criteria cri, HttpServletRequest req,
			Model model) {
		log.debug("doSelectOne or doUpdate.....");

		//현재 회원정보 불러오기
		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");

		ScheduleVO inVO = new ScheduleVO();
		inVO.setSchedule_no(schedule_no);

		ScheduleVO outVO = service.doSelectOne(inVO);

		log.debug("doSelectOne outVO: " + outVO);

		//날짜 문자열 치환
		outVO.setStart_dt(outVO.getStart_dt().replace(" ", "T"));
		outVO.setEnd_dt(outVO.getEnd_dt().replace(" ", "T"));
		
		cri.setEmployee_id(sessionVO.getEmployee_id());

		//model
		model.addAttribute("schedule", outVO);
		model.addAttribute("cri", cri);
	}

	/**
	 * 일정 검색
	 * 
	 * @param deptNo
	 * @param cri
	 * @param model
	 * @param req
	 * @author 박정민
	 */
	@RequestMapping(value = "/doSelectList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSelectList(@RequestParam(value = "dept_no", defaultValue = "0") int dept_no, Criteria cri,
			Model model, HttpServletRequest req) {
		log.debug("doSelectList loading.....");

		// 세션정보
		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");

		// 부서 리스트
		model.addAttribute("deptDiv", deptService.doSelectList());

		// 검색 리스트
		cri.setEmployee_id(sessionVO.getEmployee_id());

		if (cri.getDept_no() == 0) {
			cri.setDept_no(sessionVO.getDept_no());
		} else {
			cri.setDept_no(dept_no);
		}

		log.debug("Data: " + cri);

		model.addAttribute("list", service.doSelectList(cri));

		// 페이징
		int total = service.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));

		log.debug("doSelectList complite.....");
	}
}
