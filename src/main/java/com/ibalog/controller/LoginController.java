package com.ibalog.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibalog.annotation.NonAuth;
import com.ibalog.api.dto.AppProperties;
import com.ibalog.api.dto.LoginForm;
import com.ibalog.api.dto.LoginInfo;
import com.ibalog.service.IbaraCityService;
import com.ibalog.util.SystemLogger;

/**
 * ���O�C���t�H�[��Controller
 * @author ntmk
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final SystemLogger logger = new SystemLogger(LoginController.class);
	
	/**
	 * ���O�C�����
	 */
	@Autowired 
	private LoginInfo loginInfo;
	
	/**
	 * �A�v���P�[�V�����ݒ���
	 */
	@Autowired 
	private AppProperties appProperties;
	
	/**
	 * �t�X�T�[�r�X
	 */
	@Autowired
	private IbaraCityService ibaraCityService;

	/**
	 * ���O�C���t�H�[����\��
	 * @param mav
	 * @param model
	 * @return
	 */
	@NonAuth
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String showLoginForm(ModelAndView mav, Model model, @ModelAttribute LoginForm inputForm) {
        return "login/form";
    }
	
	/**
	 * �C�o���V�e�B�ւ̃��O�C�����s
	 * @param request
	 * @param inputForm
	 * @param bindingResult
	 * @param mav
	 * @param model
	 * @return
	 */
	@NonAuth
	@RequestMapping(value="/", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @ModelAttribute @Validated LoginForm inputForm
			, BindingResult bindingResult
			, @RequestHeader("User-Agent") String userAgent
			, ModelAndView mav, Model model) {
		
		//���̓`�F�b�N
		if (bindingResult.hasErrors()) {
			model.addAttribute("failedLogin", true);
			return "login/form";	
		}
		
		Map<String, String> cookies;
		try {
			// ���O�C������Cookie���擾���ɍs��
			cookies = ibaraCityService.getLoginCookies(inputForm.getParseENo(), inputForm.getPassword(), userAgent);
			if(ibaraCityService.checkLogin(cookies, userAgent)){
				// ���O�C�����������Ă�����ASession�ɕێ�����
				loginInfo.setLoginCookies(cookies);
				return String.format("redirect:%s/main/", appProperties.getServletPath());
			}
			
		} catch (IOException e) {
			//���O�C�����s
			logger.error(e);
		}
		
		model.addAttribute("failedLogin", true);
        return "login/form";
    }
	
}
