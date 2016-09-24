package cn.jtduan.demo;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jtduan on 2016/9/19.
 */
public enum CurrentUserUtils {
    INSTANCE;

    public final String CUR_USER = "cur_user";
    /**
     * 获取当前Request
     * @return
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取当前Session
     * @return
     */
    public HttpSession getSession() {
        return getRequest().getSession(true);
    }

    /**
     * 获取当前session里面放置的User对象
     * @return
     */
    public String getUser(){
        return (String)getSession().getAttribute(CUR_USER);
    }

    /**
     * 把当前User对象放置到session里面
     */
    public void setUser(String user){
        getSession().setAttribute(CUR_USER, user);
    }

    public boolean isAjaxRequest(){
        String requestType = getRequest().getHeader("X-Requested-With");
        if(requestType == null){
            return false;
        }
        return true;
    }
}
