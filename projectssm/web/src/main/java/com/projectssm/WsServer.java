package com.projectssm;


import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * writer: dingji
 * Time: 2021-05-01 13:00
 */
@ServerEndpoint(value="/webSocket/chat/{roomName}/{staffname}")
@Component
public class WsServer {

    // 使用map来收集session，key为roomName，value为同一个房间的用户集合
    // concurrentMap的key不存在时报错，不是返回null
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();

    @OnOpen
    public void connect(@PathParam("roomName") String roomName,@PathParam("staffname") String staffname, Session session) throws Exception {
        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }
        System.out.println("一位用户已连接!");
        broadcast(roomName, staffname+"("+session.getId()+")"+" 进入讨论组!");
    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, Session session) {
        rooms.get(roomName).remove(session);
        System.out.println("一位用户已断开连接!");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName,@PathParam("staffname") String staffname,
                           String msg, Session session) throws Exception {
        // 此处应该有html过滤
        msg =staffname + "(" + session.getId() + "):" + msg;
        System.out.println(msg);
        // 接收到信息后进行广播
        broadcast(roomName, msg);
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        for (Session session : rooms.get(roomName)) {
            session.getBasicRemote().sendText(msg);
        }
    }

}
