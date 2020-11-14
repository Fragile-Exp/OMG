package com.omg.note.service;

import java.util.HashMap;
import java.util.List;

import com.omg.cmn.Message;
import com.omg.note.domain.NoteVO;

public interface NoteService {

	/**
	 * 쪽지 삭제
	 * @param NoteVO
	 * @return Message
	 */
	Message doDelete(NoteVO note);

	/**
	 * 쪽지 보내기
	 * @param NoteVO
	 * @return flag(1:성공)
	 */
	Message doInsert(NoteVO note);

	/**
	 * 사용자 & 부서에게 쪽지 전송 메소드
	 * @param note
	 * @param div
	 * @param id
	 * @param Nm
	 * @return flag
	 */
	int sendNote(NoteVO note, int div, String id, String nm);

	/**
	 * 쪽지 선택
	 * @param NoteVO
	 * @return NoteVO
	 */
	NoteVO doSelectOne(NoteVO note);

	/**
	 * 쪽지 조회
	 * @param NoteVO
	 * @return List<NoteVO>
	 */
	List<NoteVO> doSelectList(HashMap<String, Object> map);

	/**
	 * 안 읽은 쪽지 목록
	 * @param List<NoteVO>
	 * @return
	 */
	List<NoteVO> notReadNote(String id);

}