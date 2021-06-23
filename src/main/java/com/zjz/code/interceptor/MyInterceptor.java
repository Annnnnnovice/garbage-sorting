package com.zjz.code.interceptor;

import com.zjz.code.utils.RedisUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zjz
 * @description
 * @date 2021-06-06 14:33
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 拦截前
     * @param request
     * @param response
     * @param handler
     * @return true 放行，false拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean token = RedisUtil.verifyToken(request.getHeader("token"));
        if (token != null && token) {
            return true;
        }
        return false;
    }

    /**
     * 拦截后，日志
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 清理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
