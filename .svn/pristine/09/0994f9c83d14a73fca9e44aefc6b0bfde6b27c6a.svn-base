package com.liyunet.common.chat;


import com.liyunet.common.util.UrlLoad;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{roomName}/{userId}")
public class ChatServer{
	private static Logger opLogger = LogManager.getLogger("exception");

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
    private static int onlineCount = 0;  
    private static String id = "";
    private static String name = "";
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<ChatServer>();
    private static final Map<String, CopyOnWriteArraySet<ChatServer>> rooms = new ConcurrentHashMap();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session;
  
    /** 
     * 连接建立成功调用的方法 
     *  
     * @param session 
     *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据 
     */  
    @OnOpen  
    public void onOpen(@PathParam("roomName") String roomName,@PathParam("userId") String userId, Session session) {
        this.session = session;
        // 将session按照房间名来存储，将各个房间的用户隔离
        this.id = userId;
        System.out.println("userid"+userId);
//        this.name = name;
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            CopyOnWriteArraySet<ChatServer> room = new CopyOnWriteArraySet<>();
            // 添加用户
            room.add(this);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(this);
        }
        System.out.println("a client has connected!");
    }  
  
    /** 
     * 连接关闭调用的方法 
     */  
    @OnClose  
    public void onClose(@PathParam("roomName") String roomName) {
        CopyOnWriteArraySet<ChatServer> servers = rooms.get(roomName);
        servers.remove(this); // 从set中删除
//        subOnlineCount(); // 在线数减1
    }  
  
    /** 
     * 收到客户端消息后调用的方法 
     *  
     * @param message 
     *            客户端发送过来的消息 
     * @param session 
     *            可选的参数 
     */  
    @OnMessage  
    public void onMessage(@PathParam("roomName") String roomName,String message, Session session) {
        // 群发消息  
//        for (Server item : webSocketSet) {
            try {
//                item.sendMessage(message);
                UrlLoad.load(
                        "http://127.0.0.1:8080/timetreaty_org/api/chat/addchat",
                        "languageType=zh&token="+id+"&chatinformation="+message+"&sId="+roomName);
//                UrlLoad.load(
//                        "http://127.0.0.1:8080/liyu_game/api/lucky/luckyBag",
//                        "languageType=1&token="+id+"&sum=20&groupInfoId="+roomName+"&num=15&content="+message);
                // 此处应该有html过滤
//                message = session.getId() + ":" + message;
                // 接收到信息后进行广播
                broadcast(roomName, message);
            } catch (IOException e) {
                e.printStackTrace();
//                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
    }  
    /** 
     * 发生错误时调用 
     *  
     * @param session 
     * @param error 
     */  
    @OnError  
    public void onError(Session session, Throwable error) {  
        error.printStackTrace();  
    }  
  
    /** 
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。 
     *  
     * @param message 
     * @throws IOException 
     */  
    public void sendMessage(String message) throws IOException {  
        //this.session.getBasicRemote().sendText(message);  
         this.session.getAsyncRemote().sendText(message);  
    }  
  
    public static synchronized int getOnlineCount() {  
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
    	ChatServer.onlineCount++;
    }  
  
    public static synchronized void subOnlineCount() {
        ChatServer.onlineCount--;
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        for (ChatServer session : rooms.get(roomName)) {
            session.sendMessage(msg);
        }
    }
}