package com.omg.document.domain;

import com.omg.cmn.DTO;

public class DocumentVO extends DTO {
	private String document_id;	 //문서고유번호   pk
	private String employee_id;  //사번		  fk
	private int    kind;		 //문서종류   
	private String title;        //문서제목
	private String d_day;        //처리기간
	private String document_cont;//문서내용
	private int    document_set; //문서상태
	private String ok_user;      //결재자사번    fk
	
	public DocumentVO() {}
	
	public DocumentVO(String document_id, String employee_id, int kind, String title, String d_day,
			String document_cont, int document_set, String ok_user) {
		super();
		this.document_id = document_id;
		this.employee_id = employee_id;
		this.kind = kind;
		this.title = title;
		this.d_day = d_day;
		this.document_cont = document_cont;
		this.document_set = document_set;
		this.ok_user = ok_user;
	}
	
	
	public String getDocument_id() {
		return document_id;
	}

	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
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

	public String getD_day() {
		return d_day;
	}

	public void setD_day(String d_day) {
		this.d_day = d_day;
	}

	public String getDocument_cont() {
		return document_cont;
	}

	public void setDocument_cont(String document_cont) {
		this.document_cont = document_cont;
	}

	public int getDocument_set() {
		return document_set;
	}

	public void setDocument_set(int document_set) {
		this.document_set = document_set;
	}

	public String getOk_user() {
		return ok_user;
	}

	public void setOk_user(String ok_user) {
		this.ok_user = ok_user;
	}

	@Override
	public String toString() {
		return "DocumentVO [document_id=" + document_id + ", employee_id=" + employee_id + ", kind=" + kind + ", title="
				+ title + ", d_day=" + d_day + ", document_cont=" + document_cont + ", document_set=" + document_set
				+ ", ok_user=" + ok_user + ", toString()=" + super.toString() + "]";
	}

	
	
}
