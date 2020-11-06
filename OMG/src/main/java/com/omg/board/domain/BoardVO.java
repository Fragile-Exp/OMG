package com.omg.board.domain;

import com.omg.cmn.DTO;

public class BoardVO extends DTO 

{
	private int    boardSeq; 
	private String div;
	private String title;
	private String contents;
	private int    readCnt;
	private int	   filecode;
	private String regDt;
	private String regId;
	private String modDt;
	private String modId;
	
	public BoardVO() {}

	public BoardVO(int boardSeq, String div, String title, String contents, int readCnt, int filecode, String regDt,
			String regId, String modDt, String modId) 
	{
		super();
		this.boardSeq = boardSeq;
		this.div = div;
		this.title = title;
		this.contents = contents;
		this.readCnt = readCnt;
		this.filecode = filecode;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
		this.modId = modId;
	}



	public int getBoardSeq() 
	{
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) 
	{
		this.boardSeq = boardSeq;
	}

	public String getDiv() 
	{
		return div;
	}

	public void setDiv(String div) 
	{
		this.div = div;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getContents() 
	{
		return contents;
	}

	public void setContents(String contents) 
	{
		this.contents = contents;
	}

	public int getReadCnt() 
	{
		return readCnt;
	}

	public void setReadCnt(int readCnt) 
	{
		this.readCnt = readCnt;
	}

	public int getFilecode() 
	{
		return filecode;
	}

	public void setFilecode(int file_code) 
	{
		this.filecode = filecode;
	}

	public String getRegDt() 
	{
		return regDt;
	}

	public void setRegDt(String regDt) 
	{
		this.regDt = regDt;
	}

	public String getRegId() 
	{
		return regId;
	}

	public void setRegId(String regId) 
	{
		this.regId = regId;
	}

	public String getModDt() 
	{
		return modDt;
	}

	public void setModDt(String modDt) 
	{
		this.modDt = modDt;
	}

	public String getModId() 
	{
		return modId;
	}

	public void setModId(String modId) 
	{
		this.modId = modId;
	}

	@Override
	public String toString() 
	{
		return "BoardVO [boardSeq=" + boardSeq + ", div=" + div + ", title=" + title + ", contents=" + contents
				+ ", readCnt=" + readCnt + ", filecode=" + filecode + ", regDt=" + regDt + ", regId=" + regId
				+ ", modDt=" + modDt + ", modId=" + modId + ", toString()=" + super.toString() + "]";
	}


	
	
	
	








}
