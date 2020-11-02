package com.omg.car.domain;

import com.omg.cmn.DTO;

public class CarVO extends DTO  {
	private String car_num       ; // 차량번호 pk
	private String kind          ; // 차량종류 
	private String car_use       ; // 차량연료
	private String employee_id   ; // 사번    fk
	private int    car_set       ; // 차량상태 
    private String rent_day      ; // 대여기간
    private String reason        ; // 대여사유


    public CarVO() {}


	public CarVO(String car_num, String kind, String car_use, String employee_id, int car_set, String rent_day,
			String reason) {
		super();
		this.car_num = car_num;
		this.kind = kind;
		this.car_use = car_use;
		this.employee_id = employee_id;
		this.car_set = car_set;
		this.rent_day = rent_day;
		this.reason = reason;
	}


	public String getCar_num() {
		return car_num;
	}


	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}


	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	public String getCar_use() {
		return car_use;
	}


	public void setCar_use(String car_use) {
		this.car_use = car_use;
	}


	public String getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}


	public int getCar_set() {
		return car_set;
	}


	public void setCar_set(int car_set) {
		this.car_set = car_set;
	}


	public String getRent_day() {
		return rent_day;
	}


	public void setRent_day(String rent_day) {
		this.rent_day = rent_day;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	@Override
	public String toString() {
		return "CarVO [car_num=" + car_num + ", kind=" + kind + ", car_use=" + car_use + ", employee_id=" + employee_id
				+ ", car_set=" + car_set + ", rent_day=" + rent_day + ", reason=" + reason + ", toString()="
				+ super.toString() + "]";
	}
    


}
