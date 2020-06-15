package com.ibalog.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 取得結果を格納する
 * @author ntmk
 */
public class LogResult  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public enum Result{
		Success,
		SecretError,
		FatalError
	}
	
	/**
	 * 処理結果
	 */
	private Result rslt;
	
	/**
	 * 荊ログ
	 */
	private List<IbaraLog> logs;
	
	/**
	 * 初期値を指定してLogResultをインスタンス化します
	 * @param rslt	処理結果
	 * @param logs	荊ログ
	 */
	public LogResult(Result rslt, List<IbaraLog> logs) {
		this.rslt = rslt;
		this.logs = logs;
	}
	
	/**
	 * 初期値を指定してLogResultをインスタンス化します
	 * @param rslt	処理結果
	 */
	public LogResult(Result rslt) {
		this.rslt = rslt;
		this.logs = null;
	}

	/**
	 * 処理結果を取得します
	 * @return 処理結果
	 */
	public Result getRslt() {
		return rslt;
	}
	/**
	 * 処理結果を設定します。
	 * @param rslt 処理結果
	 */
	public void setRslt(Result rslt) {
		this.rslt = rslt;
	}
	/**
	 * 荊ログを取得します。
	 * @return 荊ログ
	 */
	public List<IbaraLog> getLogs() {
		return logs;
	}
	/**
	 * 荊ログを設定します。
	 * @param logs 荊ログ
	 */
	public void setLogs(List<IbaraLog> logs) {
		this.logs = logs;
	}
	
	
}
