package com.omg.attachment.domain;

import com.omg.cmn.DTO;

public class AttachmentVO extends DTO 
{
	private String fileCode  ;
	private int fileNum      ;
	private String filePath  ;
	private String originName;
	private String saveName  ;
	
	public AttachmentVO() {}

	public AttachmentVO(String fileCode, int fileNum, String filePath, String originName, String saveName) 
	{
		super();
		this.fileCode = fileCode;
		this.fileNum = fileNum;
		this.filePath = filePath;
		this.originName = originName;
		this.saveName = saveName;
	}

	public String getFileCode() 
	{
		return fileCode;
	}

	public void setFileCode(String fileCode) 
	{
		this.fileCode = fileCode;
	}

	public int getFileNum() 
	{
		return fileNum;
	}

	public void setFileNum(int fileNum) 
	{
		this.fileNum = fileNum;
	}

	public String getFilePath() 
	{
		return filePath;
	}

	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}

	public String getOriginName() 
	{
		return originName;
	}

	public void setOriginName(String originName) 
	{
		this.originName = originName;
	}

	public String getSaveName() 
	{
		return saveName;
	}

	public void setSaveName(String saveName) 
	{
		this.saveName = saveName;
	}

	@Override
	public String toString() 
	{
		return "AttachmentVO [fileCode=" + fileCode + ", fileNum=" + fileNum + ", filePath=" + filePath
				+ ", originName=" + originName + ", saveName=" + saveName + ", toString()=" + super.toString() + "]";
	}

	

	
}
