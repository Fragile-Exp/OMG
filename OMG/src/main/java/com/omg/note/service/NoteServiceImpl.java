package com.omg.note.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.cmn.Message;
import com.omg.cmn.Search;
import com.omg.employee.dao.EmployeeDao;
import com.omg.employee.domain.EmployeeVO;
import com.omg.note.dao.NoteDaoImpl;
import com.omg.note.domain.NoteVO;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteDaoImpl dao;
	
	@Autowired
	EmployeeDao empDao;
	
	/**
	 * 쪽지 삭제
	 * @param NoteVO
	 * @return Message
	 */
	@Override
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
	@Override
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
		int checkFlag = 0;
		if(flag == 1) {
			
			// 사용자 부서에 전송
			checkFlag += sendNote(note, note.getReceiveDiv(), note.getReceiveId(), note.getReceiveNm());
			
			// 참조 처리
			// 참조가 null 이 아니면
			if((null != note.getReceiveRef()) && !note.getReceiveRef().equals("")) {
				// 참조된 사용자, 부서에 전달
				checkFlag += sendNote(note, note.getReceiveRefDiv(), note.getReceiveRef(), note.getReceiveRefNm());
			}
			
			message.setMsgId(checkFlag+"");
			message.setMsgContents("쪽지를 전송하였습니다.");
			
		} else {
			message.setMsgId(checkFlag+"");
			message.setMsgContents("쪽지 전송에 실패 하였습니다.");
		}
		
		return message;
	}
	
	/**
	 * 사용자 & 부서에게 쪽지 전송 메소드
	 * @param note
	 * @param div
	 * @param id
	 * @param Nm
	 * @return flag
	 */
	@Override
	public int sendNote(NoteVO note, int div, String id, String nm) {
		int flag = 0;
		
		// 받은 메시지함 저장
		note.setNoteDiv(2);
		
		if(div==1) { // 받는이가 사용자일 경우
			
			// 참조 비교. 받는 사람(부서) 참조(사용자)
			// 참조(사용자)의 부서가 받는 사람(부서)가 아닐때만 전송
			EmployeeVO empVO = new EmployeeVO();
			empVO.setEmployee_id(id);
			EmployeeVO outVO = empDao.doSelectOne(empVO);
			if(!(String.valueOf(outVO.getDept_no()).equals(note.getReceiveId()))) {
			
			note.setEmployeeId(id);
			note.setEmployeeNm(nm);
			flag = dao.doInsert(note);
			}
		} else {
			// 받는이가 부서일 경우

			// 해당 부서 사용자 목록 조회
			Search search = new Search();
			search.setSearchDiv("20");
			search.setSearchWord(nm);
			search.setPageNum(1);
			search.setPageSize(255);
			
			List<EmployeeVO> empList = empDao.doSelectList(search);
			if(empList.size() > 0) {
				// 부서 사용자에게 전부 전송.
				for(EmployeeVO vo : empList) {
					// 참조 비교. 받는 사람(사용자) 참조(부서)
					// 참조(부서)의 사용자가 받는 사람(사용자)가 아닐때만 전송
					if(!note.getReceiveId().equals(vo.getEmployee_id())) {
						note.setEmployeeId(vo.getEmployee_id());
						note.setEmployeeNm(vo.getName());
						flag += dao.doInsert(note);
					}
				}
			} else {
				flag += 1;
			}
			
		}
		
		return flag;
	}
	
	/**
	 * 쪽지 선택
	 * @param NoteVO
	 * @return NoteVO
	 */
	@Override
	public NoteVO doSelectOne(NoteVO note) {
		if(note.getRead()==0 && note.getNoteDiv()==2) {
			NoteVO inVO = new NoteVO();
			inVO.setRead(1);
			inVO.setNoteDiv(note.getNoteDiv());
			inVO.setNoteNo(note.getNoteNo());
			inVO.setEmployeeId(note.getEmployeeId());
			dao.doUpdateRead(inVO);
			// 보낸 메시지 함도 업데이트 처리
			inVO.setEmployeeId(note.getSenderId());
			// 싱글톤이라 임시로 이름도 비교
			inVO.setEmployeeNm(note.getSenderNm());
			inVO.setNoteDiv(1);
			dao.doUpdateRead(inVO);
		}
		
		return dao.doSelectOne(note);
	}
	
	/**
	 * 쪽지 조회
	 * @param NoteVO
	 * @return List<NoteVO>
	 */
	@Override
	public List<NoteVO> doSelectList(HashMap<String, Object> map) {
		// 아직 생각나는 로직 없음.
		// 검색 조건 추가하면 생각 나려나..
		return dao.doSelectList(map);
	}
	
	/**
	 * 안 읽은 쪽지 목록
	 * @param List<NoteVO>
	 * @return
	 */
	@Override
	public List<NoteVO> notReadNote(String id) {
		return dao.notReadNote(id);
	}
	
	
}
