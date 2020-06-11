package com.ibalog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loggerクラス
 * @author ntmk
 */
public class SystemLogger {
	
	private Logger logger;
	
	/**
	 * コンストラクタ
     * @param   cls 
     * @see org.slf4j.LoggerFactory#getLogger(Class)
     */
    public SystemLogger(Class<?> cls) {
        logger = LoggerFactory.getLogger(cls);
    }
    
    /**
     * エラー出力
     * @param   msg ログ出力内容
     * @see org.slf4j.Logger#error(String)
     */
    public void error(String msg) {
        logger.error(msg);
    }
    /**
     * エラー出力
     * @param   e 発生したException
     * @see org.slf4j.Logger#error(String)
     */
    public void error(Exception e) {
    	
    	logger.error(getExceptionMessage(e));
    	
    }

    /**
     * インフォメーション出力
     * 
     * @param   msg ログ出力内容
     * @see org.slf4j.Logger#error(String)
     */
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * デバッグ出力
     * 
     * @param   msg ログ出力内容
     * @see org.slf4j.Logger#error(String)
     */
    public void debug(String msg) {
        if (logger.isDebugEnabled())
            logger.debug(msg);
    }
    
    /**
     * デバッグ出力
     * 
     * @param   e 発生したException
     * @see org.slf4j.Logger#error(String)
     */
    public void debug(Exception e) {
    	
    	logger.debug(getExceptionMessage(e));
    	
    }
    
    private String getExceptionMessage(Exception e) {
    	StackTraceElement[] list = e.getStackTrace();
    	
    	StringBuilder build = new StringBuilder();
    	build.append(String.format("%s:%s", e.getClass(),e.getMessage()) + System.getProperty("line.separator"));
    	for( StackTraceElement s : list ) {
    		build.append(s.toString() + System.getProperty("line.separator"));
    	}
    	
    	return build.toString();
    }
}
