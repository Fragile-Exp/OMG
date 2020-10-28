package com.omg.comutting.dao;

import com.omg.cmn.DTO;

public class Commuting extends DTO {
	/** 순번 */
	private String seq;
	/** 사번 */
	private String employeeId;
	/** 이름 */
	private String name;
	/** 출근시간 */
	private String attendTime;
	/** 퇴근시간 */
	private String leaveTime;
	/** 현재상태 */
	private PresentState presentState;
	/** 출결상태 */
	private State state;
	/** 근무시간 */
	private String workTime;
	/** 등록자*/
	private String regId;
	
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
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
	@Override
	public String toString() {
		return "Comutting [seq=" + seq + ", employeeId=" + employeeId + ", name=" + name + ", attendTime=" + attendTime
				+ ", leaveTime=" + leaveTime + ", presentState=" + presentState + ", state=" + state + ", workTime="
				+ workTime + ", regId=" + regId + ", toString()=" + super.toString() + "]";
	}
	
	
	}
