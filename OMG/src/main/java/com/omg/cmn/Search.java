package com.omg.cmn;

public class Search extends DTO 
{
	private String searchDiv;
	private String searchWord;
	private int pageSize;
	private int pageNum;
	
	public Search() {}
	
	

	public Search(String searchDiv, String searchWord) 
	{
		super();
		this.pageSize = 10;
		this.pageNum = 1;
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}

	public Search(String searchDiv, String searchWord, int pageSize, int pageNum) 
	{
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}



	public String getSearchDiv() 
	{
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) 
	{
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() 
	{
		return searchWord;
	}

	public void setSearchWord(String searchWord) 
	{
		this.searchWord = searchWord;
	}

	public int getPageSize() 
	{
		return pageSize;
	}

	public void setPageSize(int pageSize) 
	{
		this.pageSize = pageSize;
	}

	public int getPageNum() 
	{
		return pageNum;
	}

	public void setPageNum(int pageNum) 
	{
		this.pageNum = pageNum;
	}



	@Override
	public String toString() 
	{
		return "Search [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", getDiv()=" + getDiv() + "]";
	}

	
	
	
	
}
