//package cn.jtduan.util.demo.websocket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.TextMessage;
//
///**
// * 注意:并没有实现有客户端向服务器发送消息的websocket代码
// */
//@Service
//public class NotificationService {
//    public void notify(String username,String msg) {
//        SpringUtil.getBean(SystemWebSocketHandler.class).sendMessageToUser("USER_1",new TextMessage(msg));
//    }
//
//    public void notifyAll(String msg) {
//        SpringUtil.getBean(SystemWebSocketHandler.class).sendMessageToUsers(new TextMessage(msg));
//    }
//
//    public boolean removeOrder(long order_id) {
//        return true;
//    }
//}