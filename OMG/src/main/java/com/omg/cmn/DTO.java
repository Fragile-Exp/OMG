package com.omg.cmn;

public class DTO {
	/**Íµ¨Î∂Ñ */
	private String div;
	/**Í∏? Î≤àÌò∏ */
	private int num;
	/**Ï¥ùÍ??àò */
	private int totalCnt;
	
	public DTO(){}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	@Override
	public String toString() {
		return "DTO [div=" + div + ", num=" + num + ", totalCnt=" + totalCnt + "]";
	}

	
	
}

