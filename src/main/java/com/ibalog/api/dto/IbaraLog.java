package com.ibalog.api.dto;

import java.io.Serializable;

/**
 * �擾�����ꏊ���O���i�[����
 * @author ntmk
 */
public class IbaraLog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * �����L�����N�^�[��
	 */
	private String cn;
	
	/**
	 * ������L�����N�^�[���@Tree
	 */
	private String tree;
	
	/**
	 * �����A�C�R��(Base64��)
	 */
	private String icnb64;
	
	/**
	 * �������e�i�R�����g�j
	 */
	private String cmnt;
	
	/**
	 * �K�v�f�[�^��ݒ肵�āA���O�f�[�^���C���X�^���X�����܂�
	 * @param characterName		�����L�����N�^�[��
	 * @param targetCharacters	�����Ώ�Tree������
	 * @param iconImageBase64	�����A�C�R��Base64
	 * @param comment			�R�����g
	 */
	public IbaraLog(String characterName, String targetCharacters, String iconImageBase64, String comment) {
		this.cn = characterName;
		this.tree = targetCharacters;
		this.icnb64 = iconImageBase64;
		this.cmnt = comment;
	}

	//--------------------------
	// Getter / Setter
	//--------------------------
	/**
	 * �����L�����N�^�[���̒l���擾���܂�
	 * @return �����L�����N�^�[��
	 */
	public String getCn() {
		return cn;
	}

	/**
	 * �����L�����N�^�[���ɒl��ݒ肵�܂�
	 * @param cn �����L�����N�^�[��
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}

	/**
	 * ������L�����N�^�[���@Tree������̒l���擾���܂�
	 * @return
	 */
	public String getTree() {
		return tree;
	}

	/**
	 * ������L�����N�^�[���@Tree������ɒl��ݒ肵�܂�
	 * @param tree
	 */
	public void setTree(String tree) {
		this.tree = tree;
	}

	/**
	 * �A�C�R��Base64������̒l���擾���܂�
	 * @return �A�C�R��Base64������
	 */
	public String getIcnb64() {
		return icnb64;
	}

	/**
	 * �A�C�R��Base64������ɒl��ݒ肵�܂�
	 * @param icnb64 �A�C�R��Base64������
	 */
	public void setIcnb64(String icnb64) {
		this.icnb64 = icnb64;
	}

	/**
	 * �R�����g�{���̒l���擾���܂�
	 * @return �R�����g�{��
	 */
	public String getCmnt() {
		return cmnt;
	}

	/**
	 * �R�����g�{���ɒl��ݒ肵�܂�
	 * @param cmnt �R�����g�{��
	 */
	public void setCmnt(String cmnt) {
		this.cmnt = cmnt;
	}

}
