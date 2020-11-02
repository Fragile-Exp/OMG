package com.omg.organization.domain;

import com.omg.cmn.DTO;

public class DeptVO extends DTO {
	/** 부서 코드 */
	private int deptNo;
	/** 부서 명 */
	private String deptNm;
	/** 부서 코드(상위 직급) */
	private int upDept;
	/** Level(단계) */
	private int level;
	
	public DeptVO(){}

	//생성자. 레벨은 생성할 때 필요 없음. (계층구조를 위한 값)
	public DeptVO(int deptNo, String deptNm, int upDept) {
		super();
		this.deptNo = deptNo;
		this.deptNm = deptNm;
		this.upDept = upDept;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public int getUpDept() {
		return upDept;
	}

	public void setUpDept(int upDept) {
		this.upDept = upDept;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "DeptVO [deptNo=" + deptNo + ", deptNm=" + deptNm + ", upDept=" + upDept + ", level=" + level
				+ ", toString()=" + super.toString() + "]";
	}
	
}
