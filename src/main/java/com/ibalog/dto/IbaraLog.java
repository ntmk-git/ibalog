package com.ibalog.dto;

import java.io.Serializable;

/**
 * 取得した場所ログを格納する
 * @author ntmk
 */
public class IbaraLog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 発言キャラクター名
	 */
	private String cn;
	
	/**
	 * 発言先キャラクター名　Tree
	 */
	private String tree;
	
	/**
	 * 発言アイコン(Base64化)
	 */
	private String icnb64;
	
	/**
	 * 投稿時間
	 */
	private String ptime;
	
	/**
	 * 場所名
	 */
	private String pname;
	
	/**
	 * 発言内容（コメント）
	 */
	private String cmnt;
	
	
	/**
	 * 必要データを設定して、ログデータをインスタンス化します
	 * @param characterName		発言キャラクター名
	 * @param targetCharacters	発言対象Tree文字列
	 * @param iconImageBase64	発言アイコンBase64
	 * @param ptime				投稿時間
	 * @param pname				場所名
	 * @param comment			コメント
	 */
	public IbaraLog(String characterName, String targetCharacters, String iconImageBase64, String ptime, String pname, String comment) {
		this.cn = characterName;
		this.tree = targetCharacters;
		this.icnb64 = iconImageBase64;
		this.cmnt = comment;
		this.ptime = ptime;
		this.pname = pname;
	}

	//--------------------------
	// Getter / Setter
	//--------------------------
	/**
	 * 発言キャラクター名の値を取得します
	 * @return 発言キャラクター名
	 */
	public String getCn() {
		return cn;
	}

	/**
	 * 発言キャラクター名に値を設定します
	 * @param cn 発言キャラクター名
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}

	/**
	 * 発言先キャラクター名　Tree文字列の値を取得します
	 * @return
	 */
	public String getTree() {
		return tree;
	}

	/**
	 * 発言先キャラクター名　Tree文字列に値を設定します
	 * @param tree
	 */
	public void setTree(String tree) {
		this.tree = tree;
	}

	/**
	 * アイコンBase64文字列の値を取得します
	 * @return アイコンBase64文字列
	 */
	public String getIcnb64() {
		return icnb64;
	}

	/**
	 * アイコンBase64文字列に値を設定します
	 * @param icnb64 アイコンBase64文字列
	 */
	public void setIcnb64(String icnb64) {
		this.icnb64 = icnb64;
	}

	/**
	 * 投稿時間の値を取得します
	 * @return 投稿時間
	 */
	public String getPtime() {
		return ptime;
	}
 
	/**
	 * 投稿時間に値を設定します
	 * @param ptime 投稿時間
	 */
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	/**
	 * 場所名の値を取得します
	 * @return 場所名
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * 場所名に値を設定します
	 * @param pname 場所名
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * コメント本文の値を取得します
	 * @return コメント本文
	 */
	public String getCmnt() {
		return cmnt;
	}

	/**
	 * コメント本文に値を設定します
	 * @param cmnt コメント本文
	 */
	public void setCmnt(String cmnt) {
		this.cmnt = cmnt;
	}

}
