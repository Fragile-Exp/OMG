package com.omg.document.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.omg.attachment.domain.AttachmentVO;
import com.omg.attachment.service.AttachmentServiceImpl;
import com.omg.cmn.Message;
import com.omg.cmn.StringUtil;
import com.omg.document.domain.DocumentVO;
import com.omg.document.service.DocumentService;
import com.omg.employee.domain.EmployeeVO;
import com.omg.organization.domain.DeptVO;
import com.omg.organization.service.DeptServiceImpl;

@Controller
public class DocumentController {
	final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	DocumentService documentService;

	@Autowired
	AttachmentServiceImpl attachmentServiceImpl;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	DeptServiceImpl deptService;

	String url = "http://localhost:8080/cmn";
	// --view--

	// --문서 목록 page
	@RequestMapping(value = "document/document.do", method = RequestMethod.GET)
	public String document_view(DocumentVO documentVO, Model model, HttpServletRequest req) {

		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");

		String Id = sessionVO.getEmployee_id();
		// 공통값 받아오기
		documentVO.setEmployeeId(Id);

		int flag = documentService.doempIdcheck(documentVO);
		model.addAttribute("flag", flag);
		LOG.debug("=doempIdcheck=" + flag);

		if (flag == 0) {
			LOG.debug("등록되어진 문서가 없습니다.");
		} else {
			List<DocumentVO> IdSeleteList = documentService.doempIdSelectList(documentVO);
			model.addAttribute("IdSeleteList", IdSeleteList);
			model.addAttribute("IdSeleteSize", IdSeleteList.size());

			LOG.debug("=IdSeleteList=" + IdSeleteList);
			LOG.debug("=IdSeleteSize=" + IdSeleteList.size());
		}

		LOG.debug("종료");
		return "document/document";
	}

	// --문서 등록page
	@RequestMapping(value = "document/document_reg.do", method = RequestMethod.GET)
	public String document_reg(Model model) {
		LOG.debug("===========================");
		LOG.debug("=document_reg.do=");
		LOG.debug("===========================");
		
		// 부서 목록
		List<DeptVO> deptList = deptService.doSelectList();
		model.addAttribute("deptList",deptList);
		
		
		return "document/document_reg";
	}

	// --문서 상세 page
	@RequestMapping(value = "document/document_info.do", method = RequestMethod.GET)
	public String document_info(DocumentVO documentVO, Model model) {

		EmployeeVO empVO = new EmployeeVO();

		DocumentVO SeleteOne = documentService.doSelectOne(documentVO);

		LOG.debug("===========================");
		LOG.debug("=doSelectOne()=" + SeleteOne);
		LOG.debug("=documentVO : " + documentVO);
		LOG.debug("===========================");

		AttachmentVO inFileVO = new AttachmentVO();
		inFileVO.setFileCode(SeleteOne.getFileCode());
		List<AttachmentVO> fileList = attachmentServiceImpl.doSelectList(inFileVO);
		LOG.debug("=fileList ="+fileList );
		
		empVO.setEmployee_id(SeleteOne.getOkUser());
		LOG.debug("empVO" + empVO);
		EmployeeVO emp = documentService.doempIdSelete(empVO);
		//EmployeeVO emp = employeeService.doSelectOne(empVO);
		LOG.debug("=emp=" + emp);

		model.addAttribute("fileList", fileList);
		model.addAttribute("SeleteOne", SeleteOne);
		model.addAttribute("emp", emp);
		
		// 부서 목록
		List<DeptVO> deptList = deptService.doSelectList();
		model.addAttribute("deptList",deptList);

		LOG.debug("SeleteOne" + SeleteOne);

		return "document/document_info";
	}

	// --관리자 목록 page
	@RequestMapping(value = "document/document_manager.do", method = RequestMethod.GET)
	public String document_manager(DocumentVO documentVO, Model model, HttpServletRequest req) {

		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");

		String Id = sessionVO.getEmployee_id();

		documentVO.setOkUser(Id);

		int flag = documentService.domanagerIdcheck(documentVO);
		model.addAttribute("flag", flag);
		LOG.debug("=domanagerIdcheck=" + flag);

		if (flag == 0) {
			LOG.debug("등록되어진 문서가 없습니다.");
		} else {
			List<DocumentVO> managerSeleteList = documentService.doSeleteListManager(documentVO);
			model.addAttribute("managerSeleteList", managerSeleteList);
			model.addAttribute("managerSeleteSize", managerSeleteList.size());
		}
		LOG.debug("종료");

		return "document/document_manager";
	}

	// --관리자 산세 page
	@RequestMapping(value = "document/document_manager_info.do", method = RequestMethod.GET)
	public String document_manager_info(DocumentVO documentVO, Model model) {
		EmployeeVO empVO = new EmployeeVO();
		DocumentVO SeleteOne = documentService.doSelectOne(documentVO);

		model.addAttribute("SeleteOne", SeleteOne);
		LOG.debug("SeleteOne" + SeleteOne);

		AttachmentVO inFileVO = new AttachmentVO();
		inFileVO.setFileCode(SeleteOne.getFileCode());
		List<AttachmentVO> fileList = attachmentServiceImpl.doSelectList(inFileVO);

		empVO.setEmployee_id(SeleteOne.getOkUser());
		LOG.debug("empVO" + empVO);
		empVO = documentService.doempIdSelete(empVO);
		LOG.debug("emp" + empVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("SeleteOne", SeleteOne);
		model.addAttribute("emp", empVO);

		LOG.debug("SeleteOne" + SeleteOne);
		
		return "document/document_manager_info";
	  
	
	
	}

	// --기능-----------------------------------------------------------------------------

	// --문서 단건 검색
	@RequestMapping(value = "document/doSelectOne.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(DocumentVO documentVO) {
		LOG.debug("==================");
		LOG.debug("=documentVO=" + documentVO);
		LOG.debug("==================");

		// documentVO.setDocumentId("E_0001");
		DocumentVO outVO = documentService.doSelectOne(documentVO);

		LOG.debug("==================");
		LOG.debug("=doSelectOne=" + outVO);
		LOG.debug("==================");

		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	// -- 문서 전체 검색
	@RequestMapping(value = "document/doSelectList.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(DocumentVO documentVO) {
		LOG.debug("==================");
		LOG.debug("=documentVO=" + documentVO);
		LOG.debug("==================");

		// documentVO.setDocumentId("E_0001");
		List<DocumentVO> outVO = documentService.doSelectList();

		LOG.debug("==================");
		LOG.debug("=doSelectList=" + outVO);
		LOG.debug("==================");

		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	// -- 문서 사원 ID 기준 검색
	@RequestMapping(value = "document/doempIdSelectList.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doempIdSelectList(DocumentVO documentVO) {
		LOG.debug("==================");
		LOG.debug("=documentVO=" + documentVO);
		LOG.debug("==================");

		// documentVO.setDocumentId("E_0001");
		List<DocumentVO> outVO = documentService.doempIdSelectList(documentVO);

		LOG.debug("==================");
		LOG.debug("=doempIdSelectList=" + outVO);
		LOG.debug("==================");

		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	// -- 삽입
	@RequestMapping(value = "document/doInsert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(DocumentVO documentVO, HttpSession session, MultipartHttpServletRequest multi)throws IllegalStateException, IOException {
		LOG.debug("==================");
		LOG.debug("=documentVO=" + documentVO);
		LOG.debug("==================");

		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");

		String Id = sessionVO.getEmployee_id();
		// 공통값 받아오기

		// 세션으로 받기
		documentVO.setEmployeeId(Id);

		int flag = documentService.doInsert(documentVO);

		LOG.debug("=doInsert=" + flag);
		Message message = new Message();
		message.setMsgId(flag + "");

		if (flag == 1) {
			String fileCode = documentVO.getFileCode();
			String dir = "document";
			List<AttachmentVO> list = StringUtil.fileUpload(multi, fileCode, dir);
			int fileFlag = 0;
			for (AttachmentVO vo : list) {
				fileFlag = attachmentServiceImpl.doInsert(vo);
			}

			message.setMsgContents(documentVO.getTitle() + "문서가 등록 되었습니다.");
		} else {
			message.setMsgContents(documentVO.getTitle() + "문서가 등록 실패 하였습니다.");
		}

		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	// -- 삭제
	@RequestMapping(value = "document/doDelete.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(DocumentVO documentVO) {
		LOG.debug("==================");
		LOG.debug("=documentVO=" + documentVO);
		LOG.debug("==================");

		int flag = documentService.doDelete(documentVO);

		LOG.debug("=doDelete=" + flag);
		Message message = new Message();
		message.setMsgId(flag + "");

		if (flag == 1) {
			message.setMsgContents(documentVO.getTitle() + "문서 삭제 성공하였습니다.");
		} else {
			message.setMsgContents(documentVO.getTitle() + "문서 삭제 실패 하였습니다.");
		}

		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	// -- 수정
	@RequestMapping(value = "document/doUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(DocumentVO documentVO, HttpSession session, MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		LOG.debug("==================");
		LOG.debug("=documentVO=" + documentVO);
		LOG.debug("==================");

		// documentVO.setDocumentId("E_0001");
		int flag = documentService.doUpdate(documentVO);

		LOG.debug("=doUpdate=" + flag);
		Message message = new Message();
		message.setMsgId(flag + "");

		if (flag == 1) {
			String fileCode = documentVO.getFileCode();
			String dir = "document";
			List<AttachmentVO> list = StringUtil.fileUpload(multi, fileCode, dir);
			LOG.debug("업로드 파일 개수 = "+list.size());
			int fileFlag = 0;
			if (list.size()>0) {
				attachmentServiceImpl.doDelete(list.get(0));
				for(AttachmentVO vo : list) {
				
					fileFlag = attachmentServiceImpl.doInsert(vo);
				}
			}

			message.setMsgContents(documentVO.getTitle() + "문서가 수정 되었습니다.");
		} else {
			message.setMsgContents(documentVO.getTitle() + "문서가 수정 실패 하였습니다.");
		}

		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	@RequestMapping(value = "document/doempName.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doempName(EmployeeVO employee, Model model) {
		String Id = documentService.doempName(employee);
		LOG.debug("Id : " + Id);
		model.addAttribute("Id", Id);

		LOG.debug("=Id=" + Id);
		Message message = new Message();
		message.setMsgId(Id + "");

		Gson gson = new Gson();
		String json = gson.toJson(Id);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}
	
	@RequestMapping(value = "document/dosetUpdate.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String dosetUpdate(DocumentVO documentVO, Model model) {
		int flag = documentService.dosetUpdate(documentVO);
		LOG.debug("Id : " + flag);
		

		Gson gson = new Gson();
		String json = gson.toJson(flag);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}
	
	
	
	
	@RequestMapping(value = "document/doempNameget.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doempNameget(EmployeeVO employee) {
		List<EmployeeVO> nameList = documentService.doempNameget(employee);
		LOG.debug("nameList" + nameList);

		Gson gson = new Gson();
		String json = gson.toJson(nameList);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;

	}

}
