package com.ibalog.api.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * application.propertiesの設定を取得・格納するクラス
 * @author ntmk
 */
@Component
public class AppProperties {

	/**
	 * spring.mvc.servlet.pathの設定値
	 */
	@Value("${spring.mvc.servlet.path}")
    private String servletPath;

	//--------------------------
	// Getter
	//--------------------------
	/**
	 * servletPathの設定値を取得します。
	 * @return servletPathの設定値
	 */
	public String getServletPath() {
		return servletPath;
	}

}
