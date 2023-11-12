package joongo.util;
import java.io.IOException;
import joongo.util.DBConn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import joongo.dao.ChatDAO;

@ServerEndpoint(value = "/chat/{roomId}")
public class ChatEndPoint {
    private static Set<Session> activeSessions = Collections.synchronizedSet(new HashSet<>());
    
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") final String roomId) {
      session.getUserProperties().put("roomId", roomId);
      activeSessions.add(session);     
    }

    @OnMessage
    public void onMessage(Session session, String message) {    
      String roomId = (String) session.getUserProperties().get("roomId");
      String rId = null;
      String uId = null;
      String text = null;
      Connection conn = null;
      
      try {//JSON 형태의 메세지를 분리해놓고 변수에 저장시킴       
    	 
    	 ObjectMapper mapper = new ObjectMapper();
         JsonNode node = mapper.readTree(message);
         rId = node.get("roomId").asText();
         uId = node.get("userId").asText();
         text = node.get("text").asText();
         
         System.out.println(rId);
         System.out.println(uId);
         System.out.println(text);
         
         conn = DBConn.getConnection();
         ChatDAO cdao = new ChatDAO(conn);
         
         boolean isSuccess = cdao.insertChatMessage(rId, uId, text);
         if (isSuccess) {
             System.out.println("저장 성공");
         } else {
             System.out.println("저장 실패");
         }
         
      } 
      catch (IOException e) {
         e.printStackTrace();
         System.out.println("예외 발생");
      }
      
      for (Session s : activeSessions) {
        if (s.isOpen() && roomId.equals(s.getUserProperties().get("roomId"))) {
          s.getAsyncRemote().sendText(message);
        }
      }
    }
    
    @OnClose
    public void onClose(Session session,CloseReason closeReason) {
      activeSessions.remove(session);
      System.out.println("Websocket closed with reason: " + closeReason.getReasonPhrase());
    }
    
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("Websocket error: " + error.getMessage());
        error.printStackTrace();
    	activeSessions.remove(session);
    }
}
