package com.ibalog.api.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibalog.api.dto.IbaraLog;
import com.ibalog.api.dto.LoginInfo;
import com.ibalog.service.IbaraCityService;
import com.ibalog.util.SystemLogger;

/**
 * IBALOG API�i�C�o���V�e�B���O�֌W�j
 * @author ntmk
 */
@RestController
@RequestMapping("api/v1/log")
public class LogRestController {
	
	private static final SystemLogger logger = new SystemLogger(LogRestController.class);
	
	/**
	 * ���O�C�����
	 */
	@Autowired 
	private LoginInfo loginInfo;
	
	/**
	 * �t�X�T�[�r�X
	 */
	@Autowired
	private IbaraCityService ibaraCityService;

	/**
	 * �ꏊNO���烍�O���擾���܂�
	 * @param placeNo	�ꏊNO
	 * @param page		�y�[�W�ԍ��i1�n�܂�j
	 * @return �C�o���V�e�B���Ojson
	 */
	@RequestMapping("place/{placeNo:[0-9]+}")
    public List<IbaraLog> getLogsByPlace(@PathVariable Integer placeNo
    		, @RequestParam(name = "page", defaultValue = "1") Integer page
    		, @RequestHeader("User-Agent") String userAgent) {
		
        try {
			return ibaraCityService.getLogs(placeNo, null, page, loginInfo.getLoginCookies(), userAgent);
			
		} catch (IOException e) {
			
			logger.error(e);
			
		}
		return null;
	}
	
	/**
	 * �����c���[���烍�O���擾���܂�
	 * @param placeNo	�ꏊNO
	 * @param treeNo	�c���[NO�iSNO�j
	 * @param page		�y�[�W�ԍ��i1�n�܂�j
	 * @return �C�o���V�e�B���Ojson
	 */
	@RequestMapping("tree/{placeNo:[0-9]+}/{treeNo:[0-9]+}")
    public List<IbaraLog> getLogsByTree(@PathVariable Integer placeNo, @PathVariable Integer treeNo
    		, @RequestParam(name = "page", defaultValue = "1") Integer page
    		, @RequestHeader("User-Agent") String userAgent) {
		try {
			return ibaraCityService.getLogs(placeNo, treeNo, page, loginInfo.getLoginCookies(), userAgent);
			
		} catch (IOException e) {
			
			logger.error(e);
			
		}
		return null;
	}

}
