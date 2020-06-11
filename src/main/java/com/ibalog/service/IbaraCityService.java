package com.ibalog.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibalog.api.dto.IbaraLog;
import com.ibalog.util.DocumentConverter;

/**
 * 荊街サービス
 * @author ntmk
 */
@Service
public class IbaraCityService {
	
	/**取得ページサイズ*/
	private final Integer PAGE_SIZE = 50;

	/**
	 * ログインURL
	 */
	private final String IBARA_CITY_LOGIN = "http://lisge.com/ib/login.php";
	/**
	 * トップ画面
	 */
	private final String IBARA_CITY_TOP = "http://lisge.com/ib";
	/**
	 * 場所取得URL
	 */
	private final String IBARA_CITY_PLACE_URL = "http://lisge.com/ib/talk.php";
	
	/**
	 * ログイン情報Cookiesを取得します。
	 * @param eno	eNo
	 * @param pass	パスワード
	 * @param userAgent	userAgent情報
	 * @return　ログイン情報Cookies
	 * @throws IOException
	 */
	public Map<String, String> getLoginCookies(Integer eno, String pass, String userAgent) throws IOException {
		
		Response loginRes = Jsoup.connect(IBARA_CITY_LOGIN)
				.userAgent(userAgent)
				.data("eno", eno.toString())
				.data("pass", pass)
				.data("jumpfile", "act_index.php")
				.data("ADR", "")
				.data("mode", "LOGIN")
				.timeout(0)
				.method(Method.POST)
                .execute();
		
		return loginRes.cookies();
	
	}
	
	/**
	 * ログインに成功したかどうか確認する
	 * @param loginCookies
	 * @param userAgent	userAgent情報
	 * @return
	 * @throws IOException
	 */
	public boolean checkLogin(Map<String, String> loginCookies, String userAgent) throws IOException{
		
		String url = UriComponentsBuilder
		        .fromHttpUrl(IBARA_CITY_TOP) 
		        .toUriString();
		
		Document document = Jsoup.connect(url)
				.userAgent(userAgent)
				.cookies(loginCookies)
				.get();
		
		DocumentConverter dc = new DocumentConverter();

		return dc.isLogin(document);
	}
	
	/**
	 * PlaceNoを指定してLogを取得します。
	 * @param userAgent	userAgent情報
	 * @return
	 * @throws IOException 
	 */
	public List<IbaraLog> getLogs(Integer placeNo, Integer treeNo, Integer page, Map<String, String> loginCookies, String userAgent) throws IOException{
		
		String url = UriComponentsBuilder
		        .fromHttpUrl(IBARA_CITY_PLACE_URL) 
		        .queryParam("dt_p", placeNo)
		        .queryParam("dt_sno", treeNo != null ? treeNo : "")
		        .queryParam("dt_kz", PAGE_SIZE)   			//＊とりあえず固定
		        .queryParam("dt_st", PAGE_SIZE * (page - 1) + 1)  //＊とりあえず固定
		        .toUriString();

		Document document = Jsoup.connect(url)
				.userAgent(userAgent)
				.cookies(loginCookies)
				.get();
		
		DocumentConverter dc = new DocumentConverter();

		return dc.docToIbaraLogList(document);
	}
	
}
