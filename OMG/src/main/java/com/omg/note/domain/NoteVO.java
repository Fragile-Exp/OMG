package com.omg.note.domain;

import com.omg.cmn.DTO;

public class NoteVO extends DTO {
	/** 쪽지 번호 */
	private int noteNo;
	/** 쪽지함 구분 */
	private int noteDiv;
	/** 보낸 이 */
	private String senderId;
	/** 보낸 이 이름 */
	private String senderNm;
	/** 받는 이 구분 */
	private int receiveDiv;
	/** 받는 이 */
	private String receiveId;
	/** 받는이 이름(사용자이름,부서이름) */
	private String receiveNm;
	/** 참조 구분 */
	private int receiveRefDiv;
	/** 참조 */
	private String receiveRef;
	/** 참조 이름 */
	private String receiveRefNm;
	/** 사원 ID(실제 조회에 사용됨) */
	private String employeeId;
	/** 사원 이름(실제 조회에 사용됨) */
	private String employeeNm;
	/** 쪽지 제목 */
	private String title;
	/** 쪽지 내용 */
	private String contents;
	/** 상위 쪽지 번호 */
	private int upNote;
	/** 읽음 확인 */
	private int read;
	/** 보낸 시간 */
	private String sendDt;
	/** 읽은 시간 */
	private String readDt;
	/** 유저 프로필 */
	private String imgName;
	
	public NoteVO() {}

	// 테스트 동안 임시 생성자
	public NoteVO(int noteNo, int noteDiv, String senderId, String senderNm, int receiveDiv, String receiveId,
			String receiveRef, String receiveNm, String title, String contents, int upNote) {
		super();
		this.noteNo = noteNo;
		this.noteDiv = noteDiv;
		this.senderId = senderId;
		this.senderNm = senderNm;
		this.receiveDiv = receiveDiv;
		this.receiveId = receiveId;
		this.receiveRef = receiveRef;
		this.receiveNm = receiveNm;
		this.title = title;
		this.contents = contents;
		this.upNote = upNote;
	}
	
	
	public NoteVO(int noteDiv, String senderId, String senderNm, int receiveDiv, String receiveId,
			String receiveRef, String receiveNm, String title, String contents, int upNote, String imgName) {
		super();
		this.noteDiv = noteDiv;
		this.senderId = senderId;
		this.senderNm = senderNm;
		this.receiveDiv = receiveDiv;
		this.receiveId = receiveId;
		this.receiveRef = receiveRef;
		this.receiveNm = receiveNm;
		this.title = title;
		this.contents = contents;
		this.upNote = upNote;
		this.imgName = imgName;
	}

	public NoteVO(int noteNo, int noteDiv, String senderId, String senderNm, int receiveDiv, String receiveId,
			String receiveNm, int receiveRefDiv, String receiveRef, String receiveRefNm, String employeeId,
			String employeeNm, String title, String contents, int upNote, int read, String sendDt, String readDt,String imgName) {
		super();
		this.noteNo = noteNo;
		this.noteDiv = noteDiv;
		this.senderId = senderId;
		this.senderNm = senderNm;
		this.receiveDiv = receiveDiv;
		this.receiveId = receiveId;
		this.receiveNm = receiveNm;
		this.receiveRefDiv = receiveRefDiv;
		this.receiveRef = receiveRef;
		this.receiveRefNm = receiveRefNm;
		this.employeeId = employeeId;
		this.employeeNm = employeeNm;
		this.title = title;
		this.contents = contents;
		this.upNote = upNote;
		this.read = read;
		this.sendDt = sendDt;
		this.readDt = readDt;
		this.imgName = imgName;
	}

	
	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public int getNoteNo() {
		return noteNo;
	}

	public void setNoteNo(int noteNo) {
		this.noteNo = noteNo;
	}

	public int getNoteDiv() {
		return noteDiv;
	}

	public void setNoteDiv(int noteDiv) {
		this.noteDiv = noteDiv;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderNm() {
		return senderNm;
	}

	public void setSenderNm(String senderNm) {
		this.senderNm = senderNm;
	}

	public int getReceiveDiv() {
		return receiveDiv;
	}

	public void setReceiveDiv(int receiveDiv) {
		this.receiveDiv = receiveDiv;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public int getReceiveRefDiv() {
		return receiveRefDiv;
	}

	public void setReceiveRefDiv(int receiveRefDiv) {
		this.receiveRefDiv = receiveRefDiv;
	}

	public String getReceiveRef() {
		return receiveRef;
	}

	public void setReceiveRef(String receiveRef) {
		this.receiveRef = receiveRef;
	}
	
	public String getReceiveRefNm() {
		return receiveRefNm;
	}

	public void setReceiveRefNm(String receiveRefNm) {
		this.receiveRefNm = receiveRefNm;
	}

	public String getReceiveNm() {
		return receiveNm;
	}

	public void setReceiveNm(String receiveNm) {
		this.receiveNm = receiveNm;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNm() {
		return employeeNm;
	}

	public void setEmployeeNm(String employeeNm) {
		this.employeeNm = employeeNm;
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getUpNote() {
		return upNote;
	}

	public void setUpNote(int upNote) {
		this.upNote = upNote;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public String getSendDt() {
		return sendDt;
	}

	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}

	public String getReadDt() {
		return readDt;
	}

	public void setReadDt(String readDt) {
		this.readDt = readDt;
	}

	@Override
	public String toString() {
		return "NoteVO [noteNo=" + noteNo + ", noteDiv=" + noteDiv + ", senderId=" + senderId + ", senderNm=" + senderNm
				+ ", receiveDiv=" + receiveDiv + ", receiveId=" + receiveId + ", receiveNm=" + receiveNm
				+ ", receiveRefDiv=" + receiveRefDiv + ", receiveRef=" + receiveRef + ", receiveRefNm=" + receiveRefNm
				+ ", employeeId=" + employeeId + ", employeeNm=" + employeeNm + ", title=" + title + ", contents="
				+ contents + ", upNote=" + upNote + ", read=" + read + ", sendDt=" + sendDt + ", readDt=" + readDt
				+ ", imgName=" + imgName + ", toString()=" + super.toString() + "]";
	}

	

	
}
