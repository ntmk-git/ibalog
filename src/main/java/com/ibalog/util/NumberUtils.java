package com.ibalog.util;

/**
 * 数字系　便利クラス
 * @author ntmk
 *
 */
public class NumberUtils {

	/**
	 * 文字列をIntegerに変換して返します。
	 * 変換できない値を引数に指定した場合はNULLを返します。
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
