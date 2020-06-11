package com.ibalog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger�N���X
 * @author ntmk
 */
public class SystemLogger {
	
	private Logger logger;
	
	/**
	 * �R���X�g���N�^
     * @param   cls 
     * @see org.slf4j.LoggerFactory#getLogger(Class)
     */
    public SystemLogger(Class<?> cls) {
        logger = LoggerFactory.getLogger(cls);
    }
    
    /**
     * �G���[�o��
     * @param   msg ���O�o�͓��e
     * @see org.slf4j.Logger#error(String)
     */
    public void error(String msg) {
        logger.error(msg);
    }
    /**
     * �G���[�o��
     * @param   e ��������Exception
     * @see org.slf4j.Logger#error(String)
     */
    public void error(Exception e) {
    	
    	logger.error(getExceptionMessage(e));
    	
    }

    /**
     * �C���t�H���[�V�����o��
     * 
     * @param   msg ���O�o�͓��e
     * @see org.slf4j.Logger#error(String)
     */
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * �f�o�b�O�o��
     * 
     * @param   msg ���O�o�͓��e
     * @see org.slf4j.Logger#error(String)
     */
    public void debug(String msg) {
        if (logger.isDebugEnabled())
            logger.debug(msg);
    }
    
    /**
     * �f�o�b�O�o��
     * 
     * @param   e ��������Exception
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
