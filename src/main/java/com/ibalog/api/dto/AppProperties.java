package com.ibalog.api.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * application.properties�̐ݒ���擾�E�i�[����N���X
 * @author ntmk
 */
@Component
public class AppProperties {

	/**
	 * spring.mvc.servlet.path�̐ݒ�l
	 */
	@Value("${spring.mvc.servlet.path}")
    private String servletPath;

	//--------------------------
	// Getter
	//--------------------------
	/**
	 * servletPath�̐ݒ�l���擾���܂��B
	 * @return servletPath�̐ݒ�l
	 */
	public String getServletPath() {
		return servletPath;
	}

}
