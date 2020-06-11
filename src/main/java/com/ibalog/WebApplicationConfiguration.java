package com.ibalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.ibalog.controller.LoginInterceptor;

/**
 * Web設定クラス
 * @author ntmk
 */
@Configuration
public class WebApplicationConfiguration {
	
	/**
	 * loginInterceptorをインスタンス化します。
	 * @return
	 */
	@Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

	/**
	 * loginInterceptorをDIコンテナに登録します。
	 * @return loginInterceptor
	 */
    @Bean
    public MappedInterceptor interceptor() {
    	// 対象：すべて
        return new MappedInterceptor(new String[]{"/**"}, loginInterceptor());
    }
}
