package it.unibo.tw.web.examples;
import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/actions")
public class WebSocketClass {
			@OnOpen
			public void open(Session session){
				
			}
			
			@OnClose
			public void close(Session session){
				
			}
			
			@OnError
			public void onError(Throwable error){
				
			}
			
			@OnMessage
			public void handleMessage(String message, Session session) throws IOException, EncodeException{
				//System.out.print(message);
				session.getBasicRemote().sendText(message);
			}
			
	}
