package com.omg.note.controller;

import java.util.HashMap;
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
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.code.dao.CodeDaoImpl;
import com.omg.code.domain.Code;
import com.omg.employee.domain.EmployeeVO;
import com.omg.note.domain.NoteVO;
import com.omg.note.service.NoteService;

@Controller
public class NoteController {
	final Logger LOG = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	NoteService noteService;
	
	@Autowired
	CodeDaoImpl codeDao;
	
	/**
	 * 읽지 않은 쪽지 불러오기
	 * @param id
	 * @return
	 */
	@RequestMapping(value="note/notReadNote.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String notReadNote(String id) {
		LOG.debug("== notReadNote ==");
		
		List<NoteVO> list = noteService.notReadNote(id);
		Gson gson=new Gson();
        String json = gson.toJson(list);
        LOG.debug("json = "+json);

		return json;
	}

	/**
	 * 사원/부서 찾기 팝업 띄우기
	 * @return
	 */
	@RequestMapping(value="note/find.do",method = RequestMethod.GET)
	public String findPage() {
		LOG.debug("== findPage ==");
		
		return "note/find";
	}
	/**
	 * 쪽지 페이지 이동
	 * @param model
	 * @param noteDiv
	 * @return
	 */
	@RequestMapping(value="note/note.do",method=RequestMethod.GET)
	public String note_view(Model model,String noteDiv) {
		LOG.debug("== note_view ==");
		if(null ==noteDiv || noteDiv.equals("")) {
			noteDiv="2";
		}
		LOG.debug("noteDiv = " + noteDiv);
		model.addAttribute("noteDiv", noteDiv);
		String codeList = "PAGE_SIZE,NOTE_CONDITION";
		List<Code> list = codeDao.doSelectList(codeList);
		
		List<Code> pageSizeList = StringUtil.getCodeSearch(list, "PAGE_SIZE");
		List<Code> noteConditionList = StringUtil.getCodeSearch(list, "NOTE_CONDITION");
		model.addAttribute("pageSizeList", pageSizeList);
		model.addAttribute("noteConditionList", noteConditionList);
		
		return "note/note";
	}
	
	/**
	 * 쪽지 보내기 페이지 이동
	 * @return
	 */
	@RequestMapping(value="note/note_reg.do",method=RequestMethod.GET)
	public String note_reg() {
		LOG.debug("== note_reg ==");
		
		return "note/note_reg";
	}
	
	/**
	 * 답글 보내기 페이지 이동
	 * @return
	 */
	@RequestMapping(value="note/note_reply.do",method=RequestMethod.GET)
	public String note_reply(NoteVO note, Model model) {
		LOG.debug("== note_reply ==");
		LOG.debug("param = "+note);
		NoteVO outVO = noteService.doSelectOne(note);
		LOG.debug("outVO = "+outVO);
		
		model.addAttribute("noteVO", outVO);
		
		return "note/note_reg";
	}
	
	/**
	 * 쪽지 읽기 페이지 이동
	 * @param note
	 * @param model
	 * @return
	 */
	@RequestMapping(value="note/note_info.do",method=RequestMethod.GET)
	public String note_info(NoteVO note, Model model) {
		LOG.debug("== note_info ==");
		LOG.debug("note = "+note);
		NoteVO noteVO = noteService.doSelectOne(note);
		
		model.addAttribute("noteVO",noteVO);
		
		return "note/note_info";
	}
	
	/**
	 * 쪽지 일괄 읽음 처리
	 * @param req
	 * @return
	 */
	@RequestMapping(value="note/changeRead.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String changeRead(HttpServletRequest req) {
		LOG.debug("== changeRead ==");
		
		// param 셋팅
		String[] noteNoList = req.getParameterValues("noteNo");
		LOG.debug("noteNoList = " + noteNoList);
		String[] employeeList = req.getParameterValues("employee");
		LOG.debug("employeeList = " + employeeList);
		String[] senderList = req.getParameterValues("sender");
		LOG.debug("senderList = " + senderList);
		String[] readList = req.getParameterValues("read");
		LOG.debug("readlist = " + readList);
		String noteDiv = req.getParameter("noteDiv");
		LOG.debug("noteDiv = " + noteDiv);
		
		int flag = 0;
		for(int i=0;i<noteNoList.length;i++) {
			NoteVO note = new NoteVO();
			note.setNoteNo(Integer.parseInt(noteNoList[i]));
			note.setEmployeeId(employeeList[i]);
			note.setSenderId(senderList[i]);
			note.setNoteDiv(Integer.parseInt(noteDiv));
			note.setRead(Integer.parseInt(readList[i]));
			NoteVO outVO = noteService.doSelectOne(note);
			flag++;
		}
		Message message = null;
		if(flag != noteNoList.length) {
			message = new Message();
			message.setMsgContents("읽음 처리 실패");
		}
		
		Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("json = "+json);

		return json;
	}
	
	/**
	 * 쪽지 삭제 이벤트
	 * @param req
	 * @return
	 */
	@RequestMapping(value="note/doDelete.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req) {
		LOG.debug("== doDelete ==");
		// param 셋팅
		String[] noteNoList = req.getParameterValues("noteNo");
		LOG.debug("noteNoList = " + noteNoList);
		String[] employeeList = req.getParameterValues("employee");
		LOG.debug("employeeList = " + employeeList);
		String noteDiv = req.getParameter("noteDiv");
		LOG.debug("noteDiv = " + noteDiv);
		
		Message message = null;
		for(int i=0;i<noteNoList.length;i++) {
			NoteVO note = new NoteVO();
			note.setNoteNo(Integer.parseInt(noteNoList[i]));
			note.setEmployeeId(employeeList[i]);
			note.setNoteDiv(Integer.parseInt(noteDiv));
			message = noteService.doDelete(note);
		}
		
		Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("json = "+json);

		return json;
	}
	
	/**
	 * 쪽지 등록 이벤트
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "note/doInsert.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(NoteVO note) {
		LOG.debug("== doInsert ==");
		
		// 쪽지 등록
		Message message = noteService.doInsert(note);
		
		Gson gson = new Gson();
        String json = gson.toJson(message);
        LOG.debug("json = "+json);
		
		return json;
	}
	
	
	/**
	 * 쪽지 목록 가져오기
	 * @param req
	 * @param search
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "note/doSelectList.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(HttpServletRequest req, NoteVO note, Search search) {
		LOG.debug("== doSelectList ==");
		// 세션 처리가 됐을 시
		EmployeeVO empVO = (EmployeeVO) req.getSession().getAttribute("employee");
		note.setEmployeeId(empVO.getEmployee_id());
		// 쪽지함 초기값
		if(note.getNoteDiv()==0) {
			note.setNoteDiv(2);
		}
		LOG.debug("note = "+note);
		// 페이지
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}
		
		// 출력 크기
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		
		//검색구분
        search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
        //검색어
        search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
        LOG.debug("search = "+search);
        
        HashMap<String, Object> map = new HashMap();
        map.put("noteVO", note);
        map.put("searchVO", search);
        
        LOG.debug("map = "+map); 
        
        List<NoteVO> list = noteService.doSelectList(map);
		
        Gson gson = new Gson();
        String json = gson.toJson(list);
        LOG.debug("json = "+json);
		
		return json;
	}

}
