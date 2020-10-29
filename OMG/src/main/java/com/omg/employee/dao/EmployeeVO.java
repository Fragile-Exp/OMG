package com.omg.employee.dao;

import com.omg.cmn.DTO;

public class EmployeeVO extends DTO {
	private String employee_id;
	private String password;
	private String name;
	private int dept_no;
	private int position_no;
	private int cell_phone;
	private String email;
	private String address;
	private String hire_date;
	private String birth_day;
	private int holiday;
	private String img_code;
	
	public EmployeeVO() {}

	public EmployeeVO(String employee_id, String password, String name, int dept_no, int position_no, int cell_phone,
			String email, String address, String hire_date, String birth_day, int holiday, String img_code) {
		super();
		this.employee_id = employee_id;
		this.password = password;
		this.name = name;
		this.dept_no = dept_no;
		this.position_no = position_no;
		this.cell_phone = cell_phone;
		this.email = email;
		this.address = address;
		this.hire_date = hire_date;
		this.birth_day = birth_day;
		this.holiday = holiday;
		this.img_code = img_code;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public int getPosition_no() {
		return position_no;
	}

	public void setPosition_no(int position_no) {
		this.position_no = position_no;
	}

	public int getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(int cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHire_date() {
		return hire_date;
	}

	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}

	public String getBirth_day() {
		return birth_day;
	}

	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}

	public int getHoliday() {
		return holiday;
	}

	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}

	public String getImg_code() {
		return img_code;
	}

	public void setImg_code(String img_code) {
		this.img_code = img_code;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", password=" + password + ", name=" + name + ", dept_no="
				+ dept_no + ", position_no=" + position_no + ", cell_phone=" + cell_phone + ", email=" + email
				+ ", address=" + address + ", hire_date=" + hire_date + ", birth_day=" + birth_day + ", holiday="
				+ holiday + ", img_code=" + img_code + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
