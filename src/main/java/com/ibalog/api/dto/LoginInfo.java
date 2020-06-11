package com.ibalog.api.dto;

import java.io.Serializable;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * �C�o���V�e�B���O�C�����
 * ���̏���Session�ɋL�������܂��B
 * @author ntmk
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ���O�C�����ɋL�����Ă���Cookie
	 */
	private Map<String, String> loginCookies;
	
	/**
	 * ���O�C�����Ă��邩�ǂ����̔���BCookie�ێ����Ă����OK�Ƃ���B
	 * @return
	 */
	public boolean isLogin() {
		return loginCookies != null;
	}

	/**
	 * ���O�C�����Cookie�̒l���擾���܂�
	 * @return ���O�C�����Cookie
	 */
	public Map<String, String> getLoginCookies() {
		return loginCookies;
	}

	/**
	 * ���O�C�����Cookie�ɒl��ݒ肵�܂�
	 * @param ���O�C�����Cookie
	 */
	public void setLoginCookies(Map<String, String> loginCookies) {
		this.loginCookies = loginCookies;
	}

}
