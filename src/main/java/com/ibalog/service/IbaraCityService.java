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
 * �t�X�T�[�r�X
 * @author ntmk
 */
@Service
public class IbaraCityService {
	
	/**�擾�y�[�W�T�C�Y*/
	private final Integer PAGE_SIZE = 50;

	/**
	 * ���O�C��URL
	 */
	private final String IBARA_CITY_LOGIN = "http://lisge.com/ib/login.php";
	/**
	 * �g�b�v���
	 */
	private final String IBARA_CITY_TOP = "http://lisge.com/ib";
	/**
	 * �ꏊ�擾URL
	 */
	private final String IBARA_CITY_PLACE_URL = "http://lisge.com/ib/talk.php";
	
	/**
	 * ���O�C�����Cookies���擾���܂��B
	 * @param eno	eNo
	 * @param pass	�p�X���[�h
	 * @param userAgent	userAgent���
	 * @return�@���O�C�����Cookies
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
	 * ���O�C���ɐ����������ǂ����m�F����
	 * @param loginCookies
	 * @param userAgent	userAgent���
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
	 * PlaceNo���w�肵��Log���擾���܂��B
	 * @param userAgent	userAgent���
	 * @return
	 * @throws IOException 
	 */
	public List<IbaraLog> getLogs(Integer placeNo, Integer treeNo, Integer page, Map<String, String> loginCookies, String userAgent) throws IOException{
		
		String url = UriComponentsBuilder
		        .fromHttpUrl(IBARA_CITY_PLACE_URL) 
		        .queryParam("dt_p", placeNo)
		        .queryParam("dt_sno", treeNo != null ? treeNo : "")
		        .queryParam("dt_kz", PAGE_SIZE)   			//���Ƃ肠�����Œ�
		        .queryParam("dt_st", PAGE_SIZE * (page - 1) + 1)  //���Ƃ肠�����Œ�
		        .toUriString();

		Document document = Jsoup.connect(url)
				.userAgent(userAgent)
				.cookies(loginCookies)
				.get();
		
		DocumentConverter dc = new DocumentConverter();

		return dc.docToIbaraLogList(document);
	}
	
}
