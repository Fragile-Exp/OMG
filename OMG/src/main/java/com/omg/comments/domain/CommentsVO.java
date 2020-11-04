package com.omg.comments.domain;

import com.omg.cmn.DTO;

public class CommentsVO extends DTO 
{
	private int 	commentNum;  
	private int 	boardSeq;
	private int 	upNum;
	private String 	contents;
	private String 	regDt;
	private String 	regId;
	private String 	modDt;
	private String	modId;
	
	public CommentsVO () {}

	public CommentsVO(int commentNum, int boardSeq, int upNum, String contents, String regDt, String regId,
			String modDt, String modId) 
	{
		super();
		this.commentNum = commentNum;
		this.boardSeq = boardSeq;
		this.upNum = upNum;
		this.contents = contents;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
		this.modId = modId;
	}

	public int getCommentNum() 
	{
		return commentNum;
	}

	public void setCommentNum(int commentNum) 
	{
		this.commentNum = commentNum;
	}

	public int getBoardSeq() 
	{
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) 
	{
		this.boardSeq = boardSeq;
	}

	public int getUpNum() 
	{
		return upNum;
	}

	public void setUpNum(int upNum) 
	{
		this.upNum = upNum;
	}

	public String getContents() 
	{
		return contents;
	}

	public void setContents(String contents) 
	{
		this.contents = contents;
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
		return "CommentsVO [commentNum=" + commentNum + ", boardSeq=" + boardSeq + ", upNum=" + upNum + ", contents="
				+ contents + ", regDt=" + regDt + ", regId=" + regId + ", modDt=" + modDt + ", modId=" + modId
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}	
