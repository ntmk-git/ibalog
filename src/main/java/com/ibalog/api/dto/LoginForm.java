package com.ibalog.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ibalog.util.NumberUtils;

/**
 * ログイン画面用フォーム
 * @author ntmk
 */
public class LoginForm {
	
	/**
	 * eno
	 */
	@NotNull(message = "ENOを指定してください。")
	@Pattern(regexp = "^[0-9]+", message = "ENOは数字で指定してください。") 
	private String eno;
	
	/**
	 * パスワード
	 */
	@NotNull(message = "パスワードを指定してください。")
	private String password;
	
	
	//--------------------------
	// Getter / Setter
	//--------------------------
	/**
	 * enoに指定された文字列をInteger型に変換して戻します。
	 * @return Integer型eno
	 */
	public Integer getParseENo() {
		return NumberUtils.tryParseInt(this.eno);
	}

	/**
	 * enoに指定された文字列を取得します
	 * @return eno
	 */
	public String getEno() {
		return eno;
	}

	/**
	 * enoに文字列を指定します。
	 * @param enoに設定したい文字列
	 */
	public void setEno(String eno) {
		this.eno = eno;
	}

	/**
	 * パスワードに指定された文字列を取得します
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードに文字列を指定します
	 * @param password パスワードに設定したい文字列
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
