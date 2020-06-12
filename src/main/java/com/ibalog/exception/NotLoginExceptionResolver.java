package com.ibalog.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ibalog.dto.LoginForm;

/**
 * NotLoginException発生時の処理
 * @author ntmk
 */
@Component
public class NotLoginExceptionResolver implements HandlerExceptionResolver {
	
	/**
	 * NotLoginExceptionが発生すると、ログインフォーム画面を表示する。
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		ModelAndView mav = new ModelAndView();
		
		LoginForm inputForm = new LoginForm();
		mav.addObject("loginForm", inputForm);
		mav.setViewName("login/form.html");
	    
	    return mav;
    
	}
	
}
