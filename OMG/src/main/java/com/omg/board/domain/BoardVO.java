package com.omg.board.domain;

import com.omg.cmn.DTO;

public class BoardVO extends DTO 

{
	private int board_seq; 
	private String div;
	private String title;
	private String contents;
	private int    read_cnt;
	private String regDt;
	private String regId;
	private String modDt;
	private String modId;
	
	public BoardVO() {}

	public BoardVO(int board_seq, String div, String title, String contents, int read_cnt, String regDt, String regId,
			String modDt, String modId) 
	{
		super();
		this.board_seq = board_seq;
		this.div = div;
		this.title = title;
		this.contents = contents;
		this.read_cnt = read_cnt;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
		this.modId = modId;
	}

	public int getBoard_seq() 
	{
		return board_seq;
	}

	public void setBoard_seq(int board_seq) 
	{
		this.board_seq = board_seq;
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

	public int getRead_cnt() 
	{
		return read_cnt;
	}

	public void setRead_cnt(int read_cnt) 
	{
		this.read_cnt = read_cnt;
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
		return "Board [board_seq=" + board_seq + ", div=" + div + ", title=" + title + ", contents=" + contents
				+ ", read_cnt=" + read_cnt + ", regDt=" + regDt + ", regId=" + regId + ", modDt=" + modDt + ", modId="
				+ modId + ", toString()=" + super.toString() + "]";
	}
	
	
	
	








}
