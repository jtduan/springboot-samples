package cn.jtduan.demo.websocket.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.UserDestinationMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by djt on 9/24/16.
 */
@Controller
public class IndexController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "")
    public String index(HttpSession session){
        session.setAttribute("sessionid",session.getId());
        return "client_stomp";
    }

    /**
     * send to response to custom user
     * @param message
     * @param principal
     * @return
     */
    @MessageMapping("/anystring")
    @SendToUser("/queue/notify")
    public String greeting(String message,Principal principal){
        System.out.println("receiving " + message);
        System.out.println("sessionId "+principal.getName());
        return "received message,"+message+":"+principal.getName();
    }

    /**
     * send response when subscribe(similiar to AJAX)
     * @param principal
     * @return
     */
    @SubscribeMapping({"/string"})
    public String handleSubscription(Principal principal) {
        return "hello,"+principal.getName();
    }

    @RequestMapping({"/one/{sessionid}"})
    @ResponseBody
    public String sendOne(@PathVariable String sessionid) {
        notificationService.notifyOne(sessionid,"custom sending to you!");
        return "success";
    }

    @RequestMapping({"/all"})
    @ResponseBody
    public String sendAll() {
        notificationService.notifyAll("broadcasting...");
        return "success";
    }
}
