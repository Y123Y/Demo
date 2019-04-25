package com.gz.example.demo.configure;

import com.gz.example.demo.common.Constant;
import com.gz.example.demo.common.ResultGenerator;
import com.gz.example.demo.pojo.User;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizedInterceptor implements HandlerInterceptor {
    @Value("${spring.profiles.active}")
    private String action;

    private static final String[] IGNORE_URI = {"/user/login", "/error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if ("dev".equals(action)) {
            Logger log = LoggerFactory.getLogger(AuthorizedInterceptor.class);
            log.debug("URL: " + request.getRequestURI());
            log.debug("Handler: " + handler.toString());
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
        RestController restController = handlerMethod.getBeanType().getAnnotation(RestController.class);

        // 默认用户没有登录
        boolean flag = false;
        // 获得请求的ServletPath
        String servletPath = request.getRequestURI();
        // 判断请求是否需要拦截
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        // 拦截请求
        if (!flag){
            // 获取session中的用户
            User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
            // 判断用户是否已经登录
            if(user == null){
                // 如果用户没有登录，跳转到登录页面
                if (responseBody == null && restController == null) {
                    response.sendRedirect("/user/login");
                }else {
                    response.getWriter().write(ResultGenerator.genUnAuthorityResultMsg().toString());
                }
            }else{
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
