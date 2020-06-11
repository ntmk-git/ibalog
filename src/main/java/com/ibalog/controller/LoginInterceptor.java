package com.ibalog.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.ibalog.annotation.NonAuth;
import com.ibalog.api.dto.LoginInfo;
import com.ibalog.exception.NotLoginException;

/**
 * ログインチェックHandlerInterceptor
 * @author ntmk
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	/**
	 * ログイン情報
	 */
	@Autowired 
	private LoginInfo loginInfo;
	
	/**
	 * 各処理の最初にログイン情報Cookieを保持しているかどうか判定する
	 * していなければNotLoginExceptionを発生させる。
	 */
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
		//静的リソースの場合は認証不要
        if (handler instanceof ResourceHttpRequestHandler) {
              return true;
        }
        
        //NonAuth付きのメソッドは無視する
		HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        NonAuth annotation = AnnotationUtils.findAnnotation(method, NonAuth.class);
        
        if (annotation != null) {
            return true;
        }
		
        if(loginInfo == null || !loginInfo.isLogin()) {
        	throw new NotLoginException();
        }
		
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
