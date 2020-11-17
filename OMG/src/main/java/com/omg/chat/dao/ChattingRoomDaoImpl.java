package com.omg.chat.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.chat.domain.ChattingRoom;

@Repository("chattingRoomDao")
public class ChattingRoomDaoImpl {
	static final Logger LOG = LoggerFactory.getLogger(ChattingRoomDaoImpl.class);
	private final String NAMESPACE = "com.omg.room";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 채팅방 목록 불러오기
	 * @return List<ChattingRoom>
	 */
	public List<ChattingRoom> doSelectList() {
		LOG.debug("== doSelectList ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectList";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		List<ChattingRoom> list = sqlSessionTemplate.selectList(statement);
		
		for(ChattingRoom vo:list) {
			LOG.debug(" 조회 VO : "+ vo);
		}
		
		return list;
	}
	
	/**
	 * 채팅방 선택
	 * @param room
	 * @return
	 */
	public ChattingRoom doSelectOne(ChattingRoom room) {
		LOG.debug("== doSelectOne ==");
		
		// mybatis 쿼리 매핑
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("statement : "+statement );
		
		// 쿼리 실행
		ChattingRoom outVO = sqlSessionTemplate.selectOne(statement,room);
		LOG.debug(" 조회 VO : "+ outVO);
		
		return outVO;
	}
	
	public int checkRoom(ChattingRoom room) {
		LOG.debug("== checkRoom ==");
		String statement = NAMESPACE+".checkRoom";
		int flag = sqlSessionTemplate.selectOne(statement,room);
		LOG.debug(" flag : "+ flag);
		
		return flag;
	}
	
	public int doInsert(ChattingRoom room) {
		LOG.debug("== doInsert ==");
		String statement = NAMESPACE+".doInsert";
		int flag = sqlSessionTemplate.insert(statement,room);
		LOG.debug(" flag : "+ flag);
		
		return flag;
	}
	
	public int doDelete(ChattingRoom room) {
		LOG.debug("== doDelete ==");
		String statement = NAMESPACE+".doDelete";
		int flag = sqlSessionTemplate.delete(statement,room);
		LOG.debug(" flag : "+ flag);
		
		return flag;
	}

}
