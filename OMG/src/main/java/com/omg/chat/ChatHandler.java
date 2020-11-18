package com.omg.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	//HashMap<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
	List<HashMap<String, Object>> sessionListMap = new ArrayList<>();
	HashMap<String,List<String>> userMap = new HashMap<String,List<String>>();
	// 방 번호를 키값으로. 해당 방에 존재하는 사용자 아이디를 list에
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지 발송
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser(msg);
		
		String rN = (String) obj.get("roomNo");
		// 받은 사용자 리스트.
		List<String> userList = userMap.get(rN);
		
		HashMap<String, Object> temp = new HashMap<String, Object>();
		if(sessionListMap.size() > 0) {
			for(int i=0; i<sessionListMap.size(); i++) {
				String roomNo = (String) sessionListMap.get(i).get("roomNo"); //세션리스트의 저장된 방번호를 가져와서
				if(roomNo.equals(rN)) { //같은값의 방이 존재한다면
					temp = sessionListMap.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
					
					
					String userName = (String) obj.get("userName");
					String type = (String) obj.get("type");
					if(type.equals("exit")) {
						userList.remove(userName);
					}else if(type.equals("enter")) {// 같은 방에 사용자가 없으면 사용자를 추가한다.
						
						if(!userList.contains(userName)) {
							userList.add(userName);
						}
					}
						
				}
					userMap.put(rN, userList);
					break;
				}
			}
			
			//해당 방의 세션들만 찾아서 메시지를 발송해준다.
			for(String k : temp.keySet()) { 
				if(k.equals("roomNo")) { //다만 방번호일 경우에는 건너뛴다.
					continue;
				}
				
				WebSocketSession wss = (WebSocketSession) temp.get(k);
				if(wss != null) {
					try {
						obj.put("userList", userList);
						wss.sendMessage(new TextMessage(obj.toJSONString()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		
		}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 소켓 연결
		super.afterConnectionEstablished(session);
		
		boolean flag = false;
		String url = session.getUri().toString();
		
		String roomNumber = url.split("/chatting/")[1];
		String roomNo = roomNumber.substring(0,roomNumber.length()-3);
		
		int idx = sessionListMap.size(); //방의 사이즈를 조사한다.
		if(sessionListMap.size() > 0) {
			for(int i=0; i<sessionListMap.size(); i++) {
				String rN = (String) sessionListMap.get(i).get("roomNo");
				if(rN.equals(roomNo)) {
					flag = true;
					idx = i;
					break;
				}
			}
		}
		
		if(flag) { //존재하는 방이라면 세션만 추가한다.
			HashMap<String, Object> map = sessionListMap.get(idx);
			map.put(session.getId(), session);
		}else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNo", roomNo);
			map.put(session.getId(), session);
			sessionListMap.add(map);
			List<String> list = new ArrayList<String>();
			userMap.put(roomNo, list);
		}
		
		//세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 소켓 종료
		super.afterConnectionClosed(session, status);
		if(sessionListMap.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
			for(int i=0; i<sessionListMap.size(); i++) {
				sessionListMap.get(i).remove(session.getId());
			}
		}
		
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
