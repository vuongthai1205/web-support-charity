/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.configs;

import com.mycompany.DTO.Message;
import com.mycompany.utils.MessageDecoder;
import com.mycompany.utils.MessageEncoder;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author vuong
 */
@Configuration
@ServerEndpoint(value = "/letschat/{login-id}")
public class WebsocketConfig {

    @OnOpen
    public void connected(Session session, @PathParam("login-id") String loggedInUser) {
        //save the logged in user id
        session.getUserProperties().put("USERNAME", loggedInUser);
        System.out.println(session.getUserProperties());
    }
    
    @OnMessage
    public void OnMessageCallback(Session session,String messageFromClient) throws IOException{
       session.getBasicRemote().sendText("got your message ");
    }

    @OnClose
    public void disconnected(Session session, CloseReason reason) {
        String peer = (String) session.getUserProperties().get("USERNAME");
        CloseReason.CloseCode closeReasonCode = reason.getCloseCode();
        String closeReasonMsg = reason.getReasonPhrase();
        System.out.println("User " + peer + " disconnected. Code: " + closeReasonCode + ", Message: " + closeReasonMsg);
    }
}
