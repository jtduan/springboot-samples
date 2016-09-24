package cn.jtduan.demo.websocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

/**
 * @author jtduan
 * @date 2016/9/23
 */
@Controller
public class WebSocketController {

    @Autowired
    private SystemWebSocketHandler systemWebSocketHandler;

    @RequestMapping("")
    public String index(HttpSession session){
        session.setAttribute("sessionid",session.getId());
        return "websocket/client";
    }

    @RequestMapping(value = "one/{username}",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String sendOne(@PathVariable String username){
        systemWebSocketHandler.sendMessageToUser(username, new TextMessage("custom message..."));
        return "SUCCESS";
    }

    @RequestMapping(value = "all",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String sendAll(){
        systemWebSocketHandler.sendMessageToUsers(new TextMessage("boardcasting..."));
        return "SUCCESS";
    }
}

