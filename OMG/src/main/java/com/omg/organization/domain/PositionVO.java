package com.omg.organization.domain;

import com.omg.cmn.DTO;

public class PositionVO extends DTO {
	/** 직급 코드 */ 
	private int positionNo;
	/** 직급 명 */
	private String positionNm;
	/** 직급 코드(상위 직급) */
	private int upPosition;
	
	public PositionVO(){}
	
	public PositionVO(int positionNo, String positionNm, int upPosition) {
		super();
		this.positionNo = positionNo;
		this.positionNm = positionNm;
		this.upPosition = upPosition;
	}

	public int getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}

	public String getPositionNm() {
		return positionNm;
	}

	public void setPositionNm(String positionNm) {
		this.positionNm = positionNm;
	}

	public int getUpPosition() {
		return upPosition;
	}

	public void setUpPosition(int upPosition) {
		this.upPosition = upPosition;
	}

	@Override
	public String toString() {
		return "PositionVO [positionNo=" + positionNo + ", positionNm=" + positionNm + ", upPosition=" + upPosition
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
