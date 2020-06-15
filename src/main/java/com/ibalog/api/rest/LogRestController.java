package com.ibalog.api.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibalog.dto.IbaraLog;
import com.ibalog.dto.LogResult;
import com.ibalog.dto.LoginInfo;
import com.ibalog.exception.SecretLogException;
import com.ibalog.service.IbaraCityService;
import com.ibalog.util.SystemLogger;

/**
 * IBALOG API（イバラシティログ関係）
 * @author ntmk
 */
@RestController
@RequestMapping("api/v1/log")
public class LogRestController {
	
	private static final SystemLogger logger = new SystemLogger(LogRestController.class);
	
	/**
	 * ログイン情報
	 */
	@Autowired 
	private LoginInfo loginInfo;
	
	/**
	 * 荊街サービス
	 */
	@Autowired
	private IbaraCityService ibaraCityService;

	/**
	 * 場所NOからログを取得します
	 * @param placeNo	場所NO
	 * @param page		ページ番号（1始まり）
	 * @return イバラシティログjson
	 */
	@RequestMapping("place/{placeNo:[0-9]+}")
    public LogResult getLogsByPlace(@PathVariable Integer placeNo
    		, @RequestParam(name = "page", defaultValue = "1") Integer page
    		, @RequestParam(name = "swords", defaultValue = "") String swords
    		, @RequestHeader("User-Agent") String userAgent) {
		
        try {
			return new LogResult(LogResult.Result.Success, ibaraCityService.getLogs(placeNo, null, swords, page, loginInfo.getLoginCookies(), userAgent));
			
		} catch (IOException e) {
			
			logger.error(e);
			
		} catch (SecretLogException e) {
			return new LogResult(LogResult.Result.SecretError);
		}
		return null;
	}
	
	/**
	 * 発言ツリーからログを取得します
	 * @param placeNo	場所NO
	 * @param treeNo	ツリーNO（SNO）
	 * @param page		ページ番号（1始まり）
	 * @return イバラシティログjson
	 */
	@RequestMapping("tree/{placeNo:[0-9]+}/{treeNo:[0-9]+}")
    public LogResult getLogsByTree(@PathVariable Integer placeNo, @PathVariable Integer treeNo
    		, @RequestParam(name = "page", defaultValue = "1") Integer page
    		, @RequestParam(name = "swords", defaultValue = "") String swords
    		, @RequestHeader("User-Agent") String userAgent) {
		try {
			return new LogResult(LogResult.Result.Success, ibaraCityService.getLogs(placeNo, treeNo, swords, page, loginInfo.getLoginCookies(), userAgent));
			
		} catch (IOException e) {
			
			logger.error(e);
			
		} catch (SecretLogException e) {
			return new LogResult(LogResult.Result.SecretError);
		}
		return null;
	}

}
