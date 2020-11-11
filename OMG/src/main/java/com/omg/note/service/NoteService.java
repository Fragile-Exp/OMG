package com.omg.note.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.cmn.Message;
import com.omg.note.dao.NoteDaoImpl;
import com.omg.note.domain.NoteVO;

@Service("noteService")
public class NoteService {

	@Autowired
	NoteDaoImpl dao;
	
	/**
	 * 쪽지 삭제
	 * @param NoteVO
	 * @return Message
	 */
	public Message doDelete(NoteVO note) {
		int flag = 0;
		Message message = new Message();
		if(note.getNoteDiv()<3) {
			flag = dao.doMoveToTrash(note);
			message.setMsgId(flag+"");
			message.setMsgContents("휴지통으로 이동 했습니다.");
		} else {
			flag = dao.doDelete(note);
			message.setMsgId(flag+"");
			message.setMsgContents("쪽지가 삭제 되었습니다.");
		}
		
		return message;
	}
	
	/**
	 * 쪽지 보내기
	 * @param NoteVO
	 * @return flag(1:성공)
	 */
	public Message doInsert(NoteVO note) {
		// 키값 가져오기
		// 테스트 동안 sequence 막기
		int key = dao.bringKey();
		note.setNoteNo(key);
		Message message = new Message();
		// 보낸 메시지함 저장
		note.setEmployeeId(note.getSenderId());
		note.setEmployeeNm(note.getSenderNm());
		int flag = dao.doInsert(note);
		
		if(flag == 1) {
			// 받은 메시지함 저장
			note.setNoteDiv(2);
			if(note.getReceiveDiv()==1) {
				// 받는이가 사용자일 경우
				note.setEmployeeId(note.getReceiveId());
				note.setEmployeeNm(note.getReceiveNm());
				flag = dao.doInsert(note);
			} else {
				// 받는이가 부서일 경우
				// 해당 부서 사용자 목록 조회 후 부서 사용자에게 전부 전달
			}
			// 참조 처리
			// 참조된 사용자, 부서에 전달
			message.setMsgId(flag+"");
			message.setMsgContents("쪽지를 전송하였습니다.");
		} else {
			message.setMsgId(flag+"");
			message.setMsgContents("쪽지 전송에 실패 하였습니다.");
		}
		
		return message;
	}
	
	/**
	 * 쪽지 선택
	 * @param NoteVO
	 * @return NoteVO
	 */
	public NoteVO doSelectOne(NoteVO note) {
		if(note.getRead()==0 && note.getNoteDiv()>1) {
			note.setRead(1);
			dao.doUpdateRead(note);
			// 보낸 메시지 함도 업데이트 처리
			note.setEmployeeId(note.getSenderId());
			// 싱글톤이라 임시로 이름도 비교
			note.setEmployeeNm(note.getSenderNm());
			note.setNoteDiv(1);
			dao.doUpdateRead(note);
		}
		
		return dao.doSelectOne(note);
	}
	
	/**
	 * 쪽지 조회
	 * @param NoteVO
	 * @return List<NoteVO>
	 */
	public List<NoteVO> doSelectList(HashMap<String, Object> map) {
		// 아직 생각나는 로직 없음.
		// 검색 조건 추가하면 생각 나려나..
		return dao.doSelectList(map);
	}
	
	
}
