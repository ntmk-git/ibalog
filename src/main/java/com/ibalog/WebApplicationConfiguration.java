package com.ibalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.ibalog.controller.LoginInterceptor;

/**
 * Web�ݒ�N���X
 * @author ntmk
 */
@Configuration
public class WebApplicationConfiguration {
	
	/**
	 * loginInterceptor���C���X�^���X�����܂��B
	 * @return
	 */
	@Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

	/**
	 * loginInterceptor��DI�R���e�i�ɓo�^���܂��B
	 * @return loginInterceptor
	 */
    @Bean
    public MappedInterceptor interceptor() {
    	// �ΏہF���ׂ�
        return new MappedInterceptor(new String[]{"/**"}, loginInterceptor());
    }
}
