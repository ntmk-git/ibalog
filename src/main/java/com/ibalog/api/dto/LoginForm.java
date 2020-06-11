package com.ibalog.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ibalog.util.NumberUtils;

/**
 * ���O�C����ʗp�t�H�[��
 * @author ntmk
 */
public class LoginForm {
	
	/**
	 * eno
	 */
	@NotNull(message = "ENO���w�肵�Ă��������B")
	@Pattern(regexp = "^[0-9]+", message = "ENO�͐����Ŏw�肵�Ă��������B") 
	private String eno;
	
	/**
	 * �p�X���[�h
	 */
	@NotNull(message = "�p�X���[�h���w�肵�Ă��������B")
	private String password;
	
	
	//--------------------------
	// Getter / Setter
	//--------------------------
	/**
	 * eno�Ɏw�肳�ꂽ�������Integer�^�ɕϊ����Ė߂��܂��B
	 * @return Integer�^eno
	 */
	public Integer getParseENo() {
		return NumberUtils.tryParseInt(this.eno);
	}

	/**
	 * eno�Ɏw�肳�ꂽ��������擾���܂�
	 * @return eno
	 */
	public String getEno() {
		return eno;
	}

	/**
	 * eno�ɕ�������w�肵�܂��B
	 * @param eno�ɐݒ肵����������
	 */
	public void setEno(String eno) {
		this.eno = eno;
	}

	/**
	 * �p�X���[�h�Ɏw�肳�ꂽ��������擾���܂�
	 * @return �p�X���[�h
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * �p�X���[�h�ɕ�������w�肵�܂�
	 * @param password �p�X���[�h�ɐݒ肵����������
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
