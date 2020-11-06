package com.omg.car.domain;

import com.omg.cmn.DTO;

public class CarVO extends DTO  {
	private String carNum       ; // 차량번호 pk
	private String kind          ; // 차량종류 
	private String carUse       ; // 차량연료
	private String employeeId   ; // 사번    fk
	private int    carSet       ; // 차량상태 
    private String rentDay      ; // 대여기간
    private String reason        ; // 대여사유

	public CarVO() {}
	
    public String getCarNum() {
		return carNum;
	}


	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}


	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	public String getCarUse() {
		return carUse;
	}


	public void setCarUse(String carUse) {
		this.carUse = carUse;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public int getCarSet() {
		return carSet;
	}


	public void setCarSet(int carSet) {
		this.carSet = carSet;
	}


	public String getRentDay() {
		return rentDay;
	}


	public void setRentDay(String rentDay) {
		this.rentDay = rentDay;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

	public CarVO(String carNum, String kind, String carUse, String employeeId, int carSet, String rentDay,
			String reason) {
		super();
		this.carNum = carNum;
		this.kind = kind;
		this.carUse = carUse;
		this.employeeId = employeeId;
		this.carSet = carSet;
		this.rentDay = rentDay;
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "CarVO [carNum=" + carNum + ", kind=" + kind + ", carUse=" + carUse + ", employeeId=" + employeeId
				+ ", carSet=" + carSet + ", rentDay=" + rentDay + ", reason=" + reason + ", toString()="
				+ super.toString() + "]";
	}


	




}
