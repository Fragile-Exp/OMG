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

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.note.domain.NoteVO;
import com.omg.note.service.NoteService;

@Controller
public class NoteController {
	final Logger LOG = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	NoteService noteService;
	
	
	@RequestMapping(value="note/note.do",method=RequestMethod.GET)
	public String note_view(Model model,String noteDiv) {
		LOG.debug("== note_view ==");
		if(null ==noteDiv || noteDiv.equals("")) {
			noteDiv="2";
		}
		LOG.debug("noteDiv = " + noteDiv);
		model.addAttribute("noteDiv", noteDiv);
		return "note/note";
	}
	
	@RequestMapping(value="note/note_reg.do",method=RequestMethod.GET)
	public String note_reg() {
		LOG.debug("== note_reg ==");
		
		return "note/note_reg";
	}
	
	@RequestMapping(value="note/note_reply.do",method=RequestMethod.GET)
	public String note_reply() {
		LOG.debug("== note_reply ==");
		
		return "redirect:note_reg.do";
	}
	
	@RequestMapping(value="note/note_info.do",method=RequestMethod.GET)
	public String note_info(NoteVO note, Model model) {
		LOG.debug("== note_info ==");
		LOG.debug("note = "+note);
		NoteVO noteVO = noteService.doSelectOne(note);
		
		model.addAttribute("noteVO",noteVO);
		
		return "note/note_info";
	}
	
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
	
	
	@RequestMapping(value = "org/doSelectList.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(HttpServletRequest req, Search search, NoteVO note) {
		LOG.debug("== doSelectList ==");
		// 세션 처리가 됐을 시
//		EmployeeVO empVO = (EmployeeVO) req.getSession().getAttribute("user");
		note.setEmployeeId("admin");
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
