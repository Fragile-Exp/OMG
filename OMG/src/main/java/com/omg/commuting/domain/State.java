package com.omg.commuting.domain;
/**
 * 출결 상태 
 *  
 *
 */
public enum State {
	
	지각조퇴(12,null),
	휴가(4,지각조퇴),
	결근(3,휴가),
	조퇴(2,결근),
	지각(1,조퇴),
	정상(0,지각);
	
	
	private final int value;
	private final State next;
	
	private State(int value,State next) {
		this.value = value;
		this.next = next;
	}
	
	// CODE return
	public int intValue() {
		return this.value;
	}
	
	//next CODE_NM return
	public State nextState() {
		return this.next;
	}
	
	// CODE -> _NM
	public static  State valueOf(int value) {
		
		switch(value) {
		case 0 : return 정상;
		case 1 : return 지각;
		case 2 : return 조퇴;
		case 3 : return 결근;
		case 4 : return 휴가;
		case 12 : return 지각조퇴;
		default:
			throw new AssertionError("Unknown value :" + value);

		}
	}
	
}
