package com.omg.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omg.board.domain.BoardVO;
import com.omg.board.service.BoardService;
import com.omg.cmn.Criteria;
import com.omg.cmn.Search;
import com.omg.code.dao.CodeDaoImpl;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.domain.PresentState;
import com.omg.commuting.service.CommutingService;
import com.omg.employee.domain.EmployeeVO;
import com.omg.schedule.domain.ScheduleVO;
import com.omg.schedule.service.ScheduleService;

@Controller
public class MainController {
	final Logger LOG = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private BoardService boardService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private CodeDaoImpl codeDaoImpl;
	
	@Autowired
	CommutingService commutingService;

	@RequestMapping(value = "view/main.do", method = RequestMethod.GET)
	public String main_view() {
		LOG.debug("== main ==");

		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startPage() {
		LOG.debug("== main ==");

		return "index";
	}

	@RequestMapping(value = "view/blank.do", method = RequestMethod.GET)
	public String blank_view() {
		LOG.debug("== user_view ==");

		return "blank";
	}

	
	@RequestMapping(value="view/main45.do",method=RequestMethod.GET)
	public String main45_view() {
		LOG.debug("== main45_view ==");
		return "index45";
	}
	
	@RequestMapping(value="view/main2.do",method=RequestMethod.GET)
	public String main2_view(HttpServletRequest req,Search search,Model model) {
		LOG.debug("== main2_view ==");

		// 1. 세션 GET
		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");

		// 2. 내 부서 게시판 불러오기
		List<BoardVO> deptBoardList = this.boardService.doSelectList(new Search("20", 5, 1));
		model.addAttribute("deptBoardList", deptBoardList);

		// 3. 공지사항 게시판 불러오기
		List<BoardVO> noticeList = this.boardService.doSelectList(new Search("10", 5, 1));
		model.addAttribute("noticeList", noticeList);

		// 4. 내 스케줄 불러오기
		Criteria cri = new Criteria();
		cri.setEmployee_id(sessionVO.getEmployee_id());
		List<ScheduleVO> scheduleList = scheduleService.toDoList(cri);
		model.addAttribute("scheduleList", scheduleList);
		
		//5. 내 부서 출근율
		Criteria criteria2 = new Criteria(1, 50, 1);
		criteria2.setDept_no(sessionVO.getDept_no());
		List<Commuting> commutingList = this.commutingService.doSelectList(criteria2);
		int totalCount = commutingService.getTotalCount(criteria2);
		int attendCount = 0;
		for(Commuting vo : commutingList) {
			if(vo.getPresentState() ==PresentState.근무중) {
				attendCount++;
			}
		}
		model.addAttribute("totalCount",totalCount); model.addAttribute("attendCount",attendCount);
		LOG.debug((attendCount/totalCount)*100.0+"");
		model.addAttribute("attendRate",(attendCount/totalCount)*100);
		
		return "index2";

	}

}
