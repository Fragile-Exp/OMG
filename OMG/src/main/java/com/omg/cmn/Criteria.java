package com.omg.cmn;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author 박정민
 */
public class Criteria {
	private int pageNum; 		// 페이지번호
	private int amount; 		// 페이지당 행의 수
	
	private int category_id;	//분류번호
	private int dept_no; 		//부서번호
	private String employee_id;	//사원번호
	private String start_dt;	//시작일
	private String end_dt;		//종료일

	private String type; 		//검색기준
	private String keyword; 	//검색어

	public Criteria() {
		this(1, 10, 1); // 페이징 시 1페이지, 10개로 초기화 + 분류번호:1
	}

	public Criteria(int pageNum, int amount, int category_id) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.category_id = category_id;
	}

	// 검색종류 문자열처리
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}

	// GET방식에 적합한 URL링크생성
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());

		return builder.toUriString();
	}

	// Getter, Setter, ToString
	public int getPageNum() {
		return pageNum;
	}

	public String getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}

	public String getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", category_id=" + category_id + ", dept_no="
				+ dept_no + ", employee_id=" + employee_id + ", start_dt=" + start_dt + ", end_dt=" + end_dt + ", type="
				+ type + ", keyword=" + keyword + "]";
	}
}
