package com.omg.documentfile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.documentfile.dao.DocumentFileDaoImpl;
import com.omg.documentfile.domain.DocumentFileVO;

@Service("documentFileService")
public class DocumentFileService {

	@Autowired
	DocumentFileDaoImpl documentFileDaoImpl;

	/**
	 * 삽입
	 * @param documentFileVO
	 * @return
	 */
	public int doInsert(DocumentFileVO documentFileVO) {
		return documentFileDaoImpl.doInsert(documentFileVO);
	}
	/**
	 * 삭제
	 * @param documentFileVO
	 * @return
	 */
	public int doDelete(DocumentFileVO documentFileVO) {
		return documentFileDaoImpl.doDelete(documentFileVO);
	}
	/**
	 * 다건 검색 
	 * @param documentFileVO
	 * @return
	 */
	public List<DocumentFileVO> doSelectList(DocumentFileVO documentFileVO) {
		return documentFileDaoImpl.doSelectList(documentFileVO);
	}
	
}
