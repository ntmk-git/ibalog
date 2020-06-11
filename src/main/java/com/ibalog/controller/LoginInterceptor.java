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
 * ���O�C���`�F�b�NHandlerInterceptor
 * @author ntmk
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	/**
	 * ���O�C�����
	 */
	@Autowired 
	private LoginInfo loginInfo;
	
	/**
	 * �e�����̍ŏ��Ƀ��O�C�����Cookie��ێ����Ă��邩�ǂ������肷��
	 * ���Ă��Ȃ����NotLoginException�𔭐�������B
	 */
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
		//�ÓI���\�[�X�̏ꍇ�͔F�ؕs�v
        if (handler instanceof ResourceHttpRequestHandler) {
              return true;
        }
        
        //NonAuth�t���̃��\�b�h�͖�������
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
