package com.omg.note.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.note.domain.NoteVO;

@Repository("noteDao")
public class NoteDaoImpl {
	static final Logger LOG = LoggerFactory.getLogger(NoteDaoImpl.class);
	private final String NAMESPACE = "com.omg.note";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	
	public NoteDaoImpl() {}
	
	/**
	 * 쪽지 삭제
	 * @param NoteVO
	 * @return flag(1:성공)
	 */
	public int doDelete(NoteVO note) {
		LOG.debug("== doDelete ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doDelete";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.delete(statement,note);
		LOG.debug(" 삭제 Flag : "+ flag);
		
		return flag;
	}
	
	/**
	 * 쪽지 보내기
	 * @param NoteVO
	 * @return flag(1:성공)
	 */
	public int doInsert(NoteVO note) {
		LOG.debug("== doInsert ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doInsert";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.insert(statement,note);
		LOG.debug(" 등록 Flag : "+ flag);
		
		return flag;
	}
	
	/**
	 * 쪽지 선택
	 * @param NoteVO
	 * @return NoteVO
	 */
	public NoteVO doSelectOne(NoteVO note) {
		LOG.debug("== doSelectOne ==");

		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		NoteVO outVO = sqlSessionTemplate.selectOne(statement,note);
		LOG.debug(" 조회 VO : "+ outVO);
		
		return outVO;
	}
	
	/**
	 * 읽음 확인
	 * @param NoteVO
	 * @return int
	 */
	public void doUpdateRead(NoteVO note) {
		LOG.debug("== doUpdateRead ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doUpdateRead";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.update(statement,note);
		LOG.debug(" 읽음 Flag : "+flag);
	}
	
	/**
	 * 휴지통 이동
	 * @param NoteVO
	 * @return int
	 */
	public int doMoveToTrash(NoteVO note) {
		LOG.debug("== doMoveToTrash ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doMoveToTrash";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int flag = sqlSessionTemplate.update(statement,note);
		LOG.debug(" 휴지통 이동 Flag : "+flag);
		return flag;
	}
	
	/**
	 * 쪽지 조회
	 * @param NoteVO
	 * @return List<NoteVO>
	 */
	public List<NoteVO> doSelectList(HashMap<String, Object> map) {
		LOG.debug("== doSelectList ==");

		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectList";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		List<NoteVO> list = sqlSessionTemplate.selectList(statement,map);
		
		for(NoteVO vo:list) {
			LOG.debug(" 조회 VO : "+ vo);
		}
		
		return list;
	}
	
	public int bringKey() {
		LOG.debug("== bringKey ==");

		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".bringKey";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		int key = sqlSessionTemplate.selectOne(statement);
		
		return key; 
	}
	
	public List<NoteVO> notReadNote(String id) {
		LOG.debug("== notReadNote ==");

		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".notReadNote";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		List<NoteVO> list = sqlSessionTemplate.selectList(statement,id);
		
		return list; 
	}
	

	
	
}
