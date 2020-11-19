package com.omg.commuting.domain;

import com.omg.cmn.DTO;

public class Commuting extends DTO {
	/* 순번 */
	private String seq;
	/* 사번 */
	private String employeeId;
	/* 이름 */
	private String name;
	/* 부서번호 */
	private String deptNo;
	/* 출근시간 */
	private String attendTime;
	/* 퇴근시간 */
	private String leaveTime;
	/* 현재상태 */
	private PresentState presentState;
	/* 출결상태 */
	private State state;
	/* 근무시간 */
	private String workTime;
	/* 등록자*/
	private String regDt;
	
	/* 현재상태 숫자코드*/
	private int presentStateIntValue;
	
	/* 금일 출결상태 숫자코드*/
	private int stateIntValue;
	
	public Commuting() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Commuting(String employeeId, String name, String deptNo) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.deptNo = deptNo;
	}
	
	


	public Commuting(String seq, String employeeId) {
		super();
		this.seq = seq;
		this.employeeId = employeeId;
	}


	public Commuting(String seq, String employeeId, String name, String deptNo, String attendTime, String leaveTime,
			PresentState presentState, State state, String workTime, String regDt) {
		super();
		this.seq = seq;
		this.employeeId = employeeId;
		this.name = name;
		this.deptNo = deptNo;
		this.attendTime = attendTime;
		this.leaveTime = leaveTime;
		this.presentState = presentState;
		this.presentStateIntValue = presentState.intValue();
		this.state = state;
		this.stateIntValue = state.intValue();
		this.workTime = workTime;
		this.regDt = regDt;
	}
	
	
	public int getPresentStateIntValue() {
		return this.presentState.intValue();
	}
	
	public int getStateIntValue() {
		return this.state.intValue();
	}
	
	
	
	public void setPresentStateIntValue(int presentStateIntValue) {
		this.presentStateIntValue = presentStateIntValue;
		this.presentState = PresentState.valueOf(presentStateIntValue);
	}


	public void setStateIntValue(int stateIntValue) {
		this.stateIntValue = stateIntValue;
		this.state = State.valueOf(stateIntValue);
	}


	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public PresentState getPresentState() {
		return presentState;
	}
	public void setPresentState(PresentState presentState) {
		this.presentState = presentState;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regId) {
		this.regDt = regId;
	}


	@Override
	public String toString() {
		return "Commuting [seq=" + seq + ", employeeId=" + employeeId + ", name=" + name + ", deptNo=" + deptNo
				+ ", attendTime=" + attendTime + ", leaveTime=" + leaveTime + ", presentState=" + presentState
				+ ", state=" + state + ", workTime=" + workTime + ", regDt=" + regDt + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
