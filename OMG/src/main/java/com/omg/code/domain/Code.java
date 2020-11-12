package com.omg.code.domain;

import com.omg.cmn.DTO;

public class Code extends DTO 
{
	private String mstCode  ;
	private String detCode  ;
	private String mstNm    ;
	private String detNm    ;
	private int    seq      ;
	private String useYn    ;
	private String regDt    ;
	private String regId    ;
	private String modDt    ;
	private String modId    ;
	
	public Code() {}

	public String getMstCode() 
	{
		return mstCode;
	}

	public void setMstCode(String mstCode) 
	{
		this.mstCode = mstCode;
	}

	public String getDetCode() 
	{
		return detCode;
	}

	public void setDetCode(String detCode) 
	{
		this.detCode = detCode;
	}

	public String getMstNm() 
	{
		return mstNm;
	}

	public void setMstNm(String mstNm) 
	{
		this.mstNm = mstNm;
	}

	public String getDetNm() 
	{
		return detNm;
	}

	public void setDetNm(String detNm) 
	{
		this.detNm = detNm;
	}

	public int getSeq() 
	{
		return seq;
	}

	public void setSeq(int seq) 
	{
		this.seq = seq;
	}

	public String getUseYn() 
	{
		return useYn;
	}

	public void setUseYn(String useYn) 
	{
		this.useYn = useYn;
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
		return "Code [mstCode=" + mstCode + ", detCode=" + detCode + ", mstNm=" + mstNm + ", detNm=" + detNm + ", seq="
				+ seq + ", useYn=" + useYn + ", regDt=" + regDt + ", regId=" + regId + ", modDt=" + modDt + ", modId="
				+ modId + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
}
