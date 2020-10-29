package com.omg.cmn;

public class Search extends DTO {
	/**
	 * 1.Í≤??ÉâÎ∂?Î∂?:?†ÑÏ≤?, ?ïÑ?ù¥?îî(10),?ù¥Î¶?(20)
	 * 2.Í≤??Éâ?ñ¥:
	 * 3.?éò?ù¥Ïß? ?Ç¨?ù¥?ä∏
	 * 4.?éò?ù¥Ïß?
	 */
	
	/**Í≤??ÉâÍµ¨Î∂Ñ: ?†ÑÏ≤?, ?ïÑ?ù¥?îî(10), ?ù¥Î¶?(20)*/
	private String searchDiv;
	/**Í≤??Éâ?ñ¥*/
	private String searchWord;
	/**?éò?ù¥Ïß? ?Ç¨?ù¥Ï¶?*/
	private int pageSize;
	/**?éò?ù¥Ïß? ?Ñò*/
	private int pageNum;
	
	public Search() {}

	
	
	
	public Search(String searchDiv, String searchWord) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.pageSize = 1;
		this.pageNum = 10;
		
	}




	public Search(String searchDiv, String searchWord, int pageSize, int pageNum) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}




	public String getSearchDiv() {
		return searchDiv;
	}




	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}




	public String getSearchWord() {
		return searchWord;
	}




	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}




	public int getPageSize() {
		return pageSize;
	}




	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}




	public int getPageNum() {
		return pageNum;
	}




	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}




	@Override
	public String toString() {
		return "Search [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
