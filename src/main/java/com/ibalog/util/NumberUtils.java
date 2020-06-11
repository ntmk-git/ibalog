package com.ibalog.util;

/**
 * �����n�@�֗��N���X
 * @author ntmk
 *
 */
public class NumberUtils {

	/**
	 * �������Integer�ɕϊ����ĕԂ��܂��B
	 * �ϊ��ł��Ȃ��l�������Ɏw�肵���ꍇ��NULL��Ԃ��܂��B
	 * @param value
	 * @return
	 */
	public static Integer tryParseInt(String value) {  
		
		try {  
			return Integer.parseInt(value);  
		} catch (NumberFormatException e) {  
			return null;  
		} 
	     
	}
}
