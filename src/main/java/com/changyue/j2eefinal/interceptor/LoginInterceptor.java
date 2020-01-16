package com.changyue.j2eefinal.interceptor;

import com.changyue.j2eefinal.service.model.UserVO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @program: j2eework-9
 * @description: 登录拦截器
 * @author: ChangYue
 * @create: 2019-12-11 09:04
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String requestUrl = httpServletRequest.getRequestURI();

        //当前的URL是否是登录的路由
        if (requestUrl.contains("login")) {
            return true;
        }

        //获取session中的登录成功的客户信息
        Object obj = httpServletRequest.getSession().getAttribute("LOGIN_USER");
        if (obj != null) {
            UserVO userVO = (UserVO) obj;
            if (userVO.getUserId() != null) {
                return true;
            }
        }

        //没有登录用户
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<html>");
        writer.println("<script>");
        writer.println("window.open('" + httpServletRequest.getContextPath() + "/tologin','_self')");
        writer.println("</script>");
        writer.println("</html>");

        return false;
    }
}
