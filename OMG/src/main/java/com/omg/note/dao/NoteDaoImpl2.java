package com.omg.note.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.omg.note.domain.NoteVO;

@Repository("noteDao2")
public class NoteDaoImpl2 {
	static final Logger LOG = LoggerFactory.getLogger(NoteDaoImpl2.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper rowMapper = new RowMapper<NoteVO>() {

		@Override
		public NoteVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			NoteVO outVO = new NoteVO();
			outVO.setNoteNo(rs.getInt("note_no"));
			outVO.setNoteDiv(rs.getInt("note_div"));
			outVO.setSenderId(rs.getString("sender_id"));
			outVO.setSenderNm(rs.getString("sender_nm"));
			outVO.setReceiveDiv(rs.getInt("receive_div"));
			outVO.setReceiveId(rs.getString("receive_id"));
			outVO.setReceiveNm(rs.getString("receive_nm"));
			outVO.setReceiveRef(rs.getString("receive_ref"));
			outVO.setEmployeeId(rs.getString("employee_id"));
			outVO.setEmployeeNm(rs.getString("employee_nm"));
			outVO.setTitle(rs.getString("title"));
			outVO.setContents(rs.getString("contents"));
			outVO.setUpNote(rs.getInt("up_note"));
			outVO.setRead(rs.getInt("read"));
			outVO.setSendDt(rs.getString("send_dt"));
			outVO.setReadDt(rs.getString("read_dt"));
			return outVO;
		}
	};
	
	public NoteDaoImpl2() {}
	
	/**
	 * 쪽지 삭제
	 * @param NoteVO
	 * @return flag(1:성공)
	 */
	public int doDelete(NoteVO note) {

		// Param Setting
		Object[] args = {note.getNoteNo(),note.getNoteDiv(),note.getEmployeeId()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM note  				\n");
		sb.append("WHERE note_no = ?			    \n");
		sb.append("AND note_div = ?					\n");
		sb.append("AND employee_id = ?					\n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : "+note);
		
		// Excute
		int flag = jdbcTemplate.update(sb.toString(),args );
		LOG.debug(" 삭제 Flag : "+ flag);
		return flag;
	}
	
	/**
	 * 쪽지 보내기
	 * @param NoteVO
	 * @return flag(1:성공)
	 */
	public int doInsert(NoteVO note) {
		
		// Param Setting
		Object[] args = {note.getNoteNo(),note.getNoteDiv(),note.getSenderId(),note.getSenderNm(),note.getReceiveDiv(),
						note.getReceiveId(),note.getReceiveRef(),note.getReceiveNm(),note.getEmployeeId(),note.getEmployeeNm(),
						note.getTitle(),note.getContents(),note.getUpNote()!=0?note.getUpNote():null};
		
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO note (    \n");
		sb.append("     note_no,          \n");
		sb.append("     note_div,         \n");
		sb.append("     sender_id,        \n");
		sb.append("     sender_nm,        \n");
		sb.append("     receive_div,      \n");
		sb.append("     receive_id,       \n");
		sb.append("     receive_ref,      \n");
		sb.append("     receive_nm,      \n");
		sb.append("     employee_id,      \n");
		sb.append("     employee_nm,      \n");
		sb.append("     title,         \n");
		sb.append("     contents,         \n");
		sb.append("     up_note           \n");
		sb.append(" ) VALUES (            \n");
		sb.append("     ?, 				  \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?,                \n");
		sb.append("     ?                 \n");
		sb.append(" )                     \n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : \n"+note);
		
		// Excute
		int flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug(" 등록 Flag : "+flag);
		
		return flag;
	}
	
	/**
	 * 쪽지 선택
	 * @param NoteVO
	 * @return NoteVO
	 */
	public NoteVO doSelectOne(NoteVO note) {

		// Param Setting
		Object[] args = {note.getNoteNo(),note.getNoteDiv(),note.getEmployeeId()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT																	\n");
		sb.append("	    note_no,       	  	     											\n");
		sb.append("	    note_div,      	        											\n");
		sb.append("	    sender_id,       	    											\n");
		sb.append("	    sender_nm,       	    											\n");
		sb.append("	    receive_div,       	  	   						 					\n");
		sb.append("	    receive_id,       	  	   											\n");
		sb.append("	    receive_ref,       	  	     										\n");
		sb.append("	    receive_nm,       	  	     										\n");
		sb.append("	    employee_id,       	  	     										\n");
		sb.append("	    employee_nm,       	  	     										\n");
		sb.append("	    title,       	  	     											\n");
		sb.append("	    contents,       	  	     										\n");
		sb.append("	    up_note,       	  	     											\n");
		sb.append("	    read,       	  	     											\n");
		sb.append("        --등록일이 당일이면:HH24MI 그렇치 않으면: YYYY-MM-DD    				\n");
		sb.append("        DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(send_dt,'YYYYMMDD')	\n");
		sb.append("           ,TO_CHAR(send_dt,'HH24:MI')                                   \n");
		sb.append("           ,TO_CHAR(send_dt,'YYYY-MM-DD')) send_dt,                      \n");
		sb.append("        DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(read_dt,'YYYYMMDD')	\n");
		sb.append("           ,TO_CHAR(read_dt,'HH24:MI')                                   \n");
		sb.append("           ,TO_CHAR(read_dt,'YYYY-MM-DD')) read_dt                       \n");
		sb.append("FROM note 	            												\n");
		sb.append("WHERE note_no = ?			   											\n");
		sb.append("AND note_div = ?															\n");
		sb.append("AND employee_id = ?														\n");
		LOG.debug("query : \n"+sb.toString());
		LOG.debug("param : \n"+note);
		
		// Excute
		NoteVO outVO = (NoteVO) jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		LOG.debug(" 조회 VO : "+ outVO);
		
		return outVO;
	}
	
	/**
	 * 읽음 확인
	 * @param NoteVO
	 * @return int
	 */
	public void doUpdateRead(NoteVO note) {
		int flag = 0;
		
		// Param Setting
		Object[] args = {note.getRead(),note.getNoteNo(),note.getNoteDiv(),note.getEmployeeId()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE note             \n");
		sb.append(" SET                     \n");
		sb.append("     read = ?,           \n");
		sb.append("     read_dt = sysdate   \n");
		sb.append("                         \n");
		sb.append(" WHERE                   \n");
		sb.append("     note_no = ?         \n");
		sb.append("     AND note_div = ?    \n");
		sb.append("     AND employee_id = ? \n");
		LOG.debug("param : \n"+note);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug(" 읽음 Flag : "+flag);
	}
	
	/**
	 * 휴지통 이동
	 * @param NoteVO
	 * @return int
	 */
	public int MoveToTrash(NoteVO note) {
		int flag = 0;
		
		// Param Setting
		Object[] args = {note.getNoteNo(),note.getNoteDiv(),note.getEmployeeId()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE note             		\n");
		sb.append(" SET                     		\n");
		sb.append("     note_div = note_div+10      \n");
		sb.append(" WHERE                   		\n");
		sb.append("     note_no = ?         		\n");
		sb.append("     AND note_div = ?    		\n");
		sb.append("     AND employee_id = ? 		\n");
		LOG.debug("param : \n"+note);
		
		// Excute
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug(" 휴지통 이동 Flag : "+flag);
		return flag;
	}
	
	/**
	 * 쪽지 조회
	 * @param NoteVO
	 * @return List<NoteVO>
	 */
	public List<NoteVO> doSelectList(NoteVO note) {

		// 검색조건은 추후 
		
		// Param Setting
		Object[] args = {note.getNoteDiv(),note.getEmployeeId()};
		// Query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT																	\n");
		sb.append("	    note_no,       	  	     											\n");
		sb.append("	    note_div,      	        											\n");
		sb.append("	    sender_id,       	    											\n");
		sb.append("	    sender_nm,       	    											\n");
		sb.append("	    receive_div,       	  	   						 					\n");
		sb.append("	    receive_id,       	  	   											\n");
		sb.append("	    receive_ref,       	  	     										\n");
		sb.append("	    receive_nm,       	  	     										\n");
		sb.append("	    employee_id,       	  	     										\n");
		sb.append("	    employee_nm,       	  	     										\n");
		sb.append("	    title,       	  	     											\n");
		sb.append("	    contents,       	  	     										\n");
		sb.append("	    up_note,       	  	     											\n");
		sb.append("	    read,       	  	     											\n");
		sb.append("        --등록일이 당일이면:HH24MI 그렇치 않으면: YYYY-MM-DD    				\n");
		sb.append("        DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(send_dt,'YYYYMMDD')	\n");
		sb.append("           ,TO_CHAR(send_dt,'HH24:MI')                                   \n");
		sb.append("           ,TO_CHAR(send_dt,'YYYY-MM-DD')) send_dt,                      \n");
		sb.append("        DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(read_dt,'YYYYMMDD')	\n");
		sb.append("           ,TO_CHAR(read_dt,'HH24:MI')                                   \n");
		sb.append("           ,TO_CHAR(read_dt,'YYYY-MM-DD')) read_dt                       \n");
		sb.append("FROM note 	            												\n");
		sb.append("WHERE note_div = ?														\n");
		sb.append("AND employee_id = ?														\n");
		sb.append("ORDER BY send_dt desc													\n");
		LOG.debug("param : \n"+note);
		LOG.debug("query : \n"+sb.toString());
		
		// Excute
		List<NoteVO> list = (List<NoteVO>) jdbcTemplate.query(sb.toString(), args, rowMapper);
		
		for(NoteVO vo:list) {
			LOG.debug(" 조회 VO : "+ vo);
		}
		
		return list;
	}
	
	public int bringKey() {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT note_seq.nextval FROM dual");
		LOG.debug("query : \n"+sb.toString());
		
		int key = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		
		return key; 
	}
	

	
	
}
