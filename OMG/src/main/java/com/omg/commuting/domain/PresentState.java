package com.omg.commuting.domain;
/**
 * 현재 출결 
 *
 */
public enum PresentState {
	
	자리비움(30,null),퇴근(20,자리비움),근무중(10,퇴근),대기중(0,근무중);
	
	private final int value;
	private final PresentState next;
	
	PresentState(int value,PresentState next) {
		this.value = value;
		this.next = next;
	}
	
	// CODE return
	public int intValue() {
		return this.value;
	}
	
	//next CODE_NM return
	public PresentState nextState() {
		return this.next;
	}
	
	// CODE -> _NM
	public static  PresentState valueOf(int value) {
		
		switch(value) {
		case 10 : return 근무중;
		case 20 : return 퇴근;
		case 30 : return 자리비움;
		case  0 : return 대기중;
		
		default:
			throw new AssertionError("Unknown value :" + value);

		}
	}
}
