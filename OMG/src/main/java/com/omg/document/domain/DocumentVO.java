package com.omg.document.domain;

import com.omg.cmn.DTO;

public class DocumentVO extends DTO {
	private String documentId;	 //문서고유번호   pk
	private String employeeId;  //사번		  fk
	private int    kind;		 //문서종류   
	private String title;        //문서제목
	private String dDay;        //처리기간
	private String documentCont;//문서내용
	private int    documentSet; //문서상태
	private String okUser;      //결재자사번    fk
	private String fileCode;
	
	
	
	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public DocumentVO() {}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getdDay() {
		return dDay;
	}

	public void setdDay(String dDay) {
		this.dDay = dDay;
	}

	public String getDocumentCont() {
		return documentCont;
	}

	public void setDocumentCont(String documentCont) {
		this.documentCont = documentCont;
	}

	public int getDocumentSet() {
		return documentSet;
	}

	public void setDocumentSet(int documentSet) {
		this.documentSet = documentSet;
	}

	public String getOkUser() {
		return okUser;
	}

	public void setOkUser(String okUser) {
		this.okUser = okUser;
	}

	public DocumentVO(String documentId, String employeeId, int kind, String title, String dDay, String documentCont,
			int documentSet, String okUser, String fileCode) {
		super();
		this.documentId = documentId;
		this.employeeId = employeeId;
		this.kind = kind;
		this.title = title;
		this.dDay = dDay;
		this.documentCont = documentCont;
		this.documentSet = documentSet;
		this.okUser = okUser;
		this.fileCode = fileCode;
	}

	@Override
	public String toString() {
		return "DocumentVO [documentId=" + documentId + ", employeeId=" + employeeId + ", kind=" + kind + ", title="
				+ title + ", dDay=" + dDay + ", documentCont=" + documentCont + ", documentSet=" + documentSet
				+ ", okUser=" + okUser + ", fileCode=" + fileCode + ", toString()=" + super.toString() + "]";
	}

	
	
	
	
}
