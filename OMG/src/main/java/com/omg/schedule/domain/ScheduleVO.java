package com.omg.schedule.domain;

import com.omg.cmn.DTO;

public class ScheduleVO extends DTO {
    private int scheduleNo;	//일정번호
    private int deptNo;		//부서번호
    private String employeeId;	//사번
    private int categoryId;	//카테고리ID
    private int timeStatus;	//일정상태
    private String title;	//제목
    private String content;	//내용
    private String startDt;	//시작일
    private String endDt;	//종료일
    
    public ScheduleVO() {}

    public ScheduleVO(int scheduleNo, int deptNo, String employeeId, int categoryId, int timeStatus, String title,
	    String content, String startDt, String endDt) {
	this.scheduleNo = scheduleNo;
	this.deptNo = deptNo;
	this.employeeId = employeeId;
	this.categoryId = categoryId;
	this.timeStatus = timeStatus;
	this.title = title;
	this.content = content;
	this.startDt = startDt;
	this.endDt = endDt;
    }

    public int getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(int scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(int timeStatus) {
        this.timeStatus = timeStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    @Override
    public String toString() {
	return "ScheduleVO [scheduleNo=" + scheduleNo + ", deptNo=" + deptNo + ", employeeId=" + employeeId
		+ ", categoryId=" + categoryId + ", timeStatus=" + timeStatus + ", title=" + title + ", content="
		+ content + ", startDt=" + startDt + ", endDt=" + endDt + "]";
    }

}
