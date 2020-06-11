package com.ibalog.api.dto;

import java.io.Serializable;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * イバラシティログイン情報
 * この情報はSessionに記憶させます。
 * @author ntmk
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ログイン時に記憶しておくCookie
	 */
	private Map<String, String> loginCookies;
	
	/**
	 * ログインしているかどうかの判定。Cookie保持していればOKとする。
	 * @return
	 */
	public boolean isLogin() {
		return loginCookies != null;
	}

	/**
	 * ログイン情報Cookieの値を取得します
	 * @return ログイン情報Cookie
	 */
	public Map<String, String> getLoginCookies() {
		return loginCookies;
	}

	/**
	 * ログイン情報Cookieに値を設定します
	 * @param ログイン情報Cookie
	 */
	public void setLoginCookies(Map<String, String> loginCookies) {
		this.loginCookies = loginCookies;
	}

}
