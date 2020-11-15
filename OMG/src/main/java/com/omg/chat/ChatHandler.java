package com.omg.chat;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {
	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지 발송
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser(msg);
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			
			wss.sendMessage(new TextMessage(obj.toJSONString()));
		}
		
		super.handleTextMessage(session, message);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 소켓 연결
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(),session);
		
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 소켓 종료
		super.afterConnectionClosed(session, status);
		sessionMap.remove(session.getId());
	}
	
	// json을 파싱해주는 함수
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
