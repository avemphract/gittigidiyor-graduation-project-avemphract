package dev.patika.customerserver.config;

import dev.patika.customerserver.utils.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterception implements AsyncHandlerInterceptor {

    @Autowired
    RequestInfo requestInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestInfo.setClientUrl(request.getRemoteAddr());
        requestInfo.setSessionId(request.getSession().getId());
        requestInfo.setRequestURI(request.getRequestURL().toString());
        return true;
    }
}
