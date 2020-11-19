package com.omg.document.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.document.dao.DocumentDaoImpl;
import com.omg.document.domain.DocumentVO;
import com.omg.employee.domain.EmployeeVO;

@Service("documentService")
public class DocumentService {

	@Autowired
	DocumentDaoImpl documentDaoImpl;
	
	/**
	 * 문서 삽입
	 * @param documentVO
	 * @return
	 */
	public int doInsert(DocumentVO documentVO) {
		return documentDaoImpl.doInsert(documentVO);
	}
	
	/**
	 * 문서 삭제
	 * @param documentVO
	 * @return
	 */
	public int doDelete(DocumentVO documentVO) {
		return documentDaoImpl.doDelete(documentVO);
	}
	
	/**
	 * 문서 갱신
	 * @param documentVO
	 * @return
	 */
	public int doUpdate(DocumentVO documentVO) {
		return documentDaoImpl.doUpdate(documentVO);
	}
	/**
	 * 문서단건 검색
	 * @param documentVO
	 * @return
	 */
	public DocumentVO doSelectOne(DocumentVO documentVO) {
		return documentDaoImpl.doSelectOne(documentVO);
	}
	
	/**
	 * 문서 전체 검색
	 * @param documentVO
	 * @return
	 */
	public List<DocumentVO> doSelectList(){
		return documentDaoImpl.doSelectList();
	}
	
	/**
	 * 문서 사원 ID 기준 검색
	 * @param documentVO
	 * @return
	 */
	
	public List<DocumentVO> doempIdSelectList (DocumentVO documentVO){
		
		return documentDaoImpl.doempIdSelectList(documentVO);
	}
	
	/**
	 * 해당 ID 문서 내역 검색
	 * @param documentVO
	 * @return
	 */
	public int doempIdcheck(DocumentVO documentVO) {
		return documentDaoImpl.doempIdcheck(documentVO);
	}
	
	/**
	 * 문서 전체 갯수 
	 * @param documentVO
	 * @return
	 */
	public int doSeleteAllCount(DocumentVO documentVO) {
		return documentDaoImpl.doSeleteAllCount(documentVO);
	}

	/**
	 * 문서 ID 있는지 검사
	 * @param documentVO
	 * @return
	 */
	public int doovercheck(DocumentVO documentVO) {
		return documentDaoImpl.doovercheck(documentVO);
	}
	
	/**
	 * 관리자 기준 문서목록 검색 
	 * @param documentVO
	 * @return
	 */
	public List<DocumentVO> doSeleteListManager (DocumentVO documentVO){
		return documentDaoImpl.doSeleteListManager(documentVO);
	}
	
	/**
	 * 관리자 목록 갯수  
	 * @param documentVO
	 * @return
	 */
	public int domanagerIdcheck(DocumentVO documentVO){
		return documentDaoImpl.domanagerIdcheck(documentVO);
	}

	/**
	 * 결쟂자 name 찾기
	 * @param employee
	 * @return
	 */
	public String doempName (EmployeeVO employee){
		return documentDaoImpl.doempName(employee);
	}

	public EmployeeVO doempIdSelete (EmployeeVO employee){
		return documentDaoImpl.doempIdSelete(employee);
	}

	public int doMaxNumberId(DocumentVO documentVO) {
		return documentDaoImpl.doMaxNumberId(documentVO);
	}

	public List<EmployeeVO> doempNameget(EmployeeVO employee) {
		return documentDaoImpl.doempNameget(employee);
	}

	
	
	public int dodocumentenroll(DocumentVO documentVO) {
		return documentDaoImpl.dodocumentenroll(documentVO);
	}
	public int dodocumentapp(DocumentVO documentVO) {
		return documentDaoImpl.dodocumentapp(documentVO);
	}

	public int dosetUpdate(DocumentVO documentVO) {
		return documentDaoImpl.dosetUpdate(documentVO);
	}



}
