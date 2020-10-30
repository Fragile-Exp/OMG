package com.omg.schedule.domain;

public class ScheduleCatVO {
    
    private int categoryId;
    private String categoryNm;
    
    public ScheduleCatVO() {}

    public ScheduleCatVO(int categoryId, String categoryNm) {
	this.categoryId = categoryId;
	this.categoryNm = categoryNm;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    @Override
    public String toString() {
	return "ScheduleCatVO [categoryId=" + categoryId + ", categoryNm=" + categoryNm + "]";
    }
    
}
