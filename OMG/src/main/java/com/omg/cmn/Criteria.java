package com.omg.cmn;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author 박정민
 */
public class Criteria {
	private int pageNum; // 페이지번호
	private int amount; // 페이지당 행의 수
	private int deptNo; //부서번호

	private String type; // 검색기준
	private String keyword; // 검색어

	public Criteria() {
		this(1, 10); // 페이징 시 1페이지, 10개로 초기화
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
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

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
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
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", deptNo=" + deptNo + ", type=" + type
				+ ", keyword=" + keyword + "]";
	}
	
}
