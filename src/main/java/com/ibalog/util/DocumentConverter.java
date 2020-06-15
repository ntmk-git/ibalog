package com.ibalog.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ibalog.dto.IbaraLog;
import com.ibalog.exception.SecretLogException;

/**
 * jsoup doc to IbaraLog List
 * @author ntmk
 */
public class DocumentConverter {
	
	/**
	 * ログインしている画面かどうかを判定する
	 * ※ひとまず　<td class="F5B">Jump</td> が存在していないことを条件とする
	 * @param document
	 * @return ログイン中：True／未ログイン：False
	 */
	public Boolean isLogin(Document document) {
		
		Elements wakuList = document.body().select(".MIGIWAKU");
		for(Element rWaku : wakuList) {
			Elements cEle = rWaku.select(".F5B");
			
			Boolean hasF5BJump = false;
			
			for(Element ele : cEle) {
				if("Jump".equals(ele.html())) {
					hasF5BJump = true;
				}
			}
			
			if(hasF5BJump) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * DocumentをIbaraLogへ変換する
	 * @param document
	 * @return
	 * @throws SecretLogException 
	 */
	public List<IbaraLog> docToIbaraLogList(Document document) throws SecretLogException{
		
		if(isSecretLog(document)) {
			throw new SecretLogException();
		}
		
		List<IbaraLog> result = new ArrayList<IbaraLog>();
		
		Elements tableList = document.body().select("table.SE0");
		for(Element tbl : tableList) {
			Elements trList = tbl.select("tr");
			
			Boolean hasImage = false;
			String iconImageBase64 = "";
			String characterName = "";
			String targetCharacters = "";
			String comment = "";
			String postTime = "";
			String placeName = "";
			
			for(Element tr : trList) {
				
				if("TOP".equals(tr.attr("valign"))) {
					Elements tdList = tr.select("td");
					for(Element td : tdList) {
						
						Elements imgEles = td.select("img");
						if(imgEles.size() > 0) {
							if(imgEles.get(0).nodeName().equals("img")) {
								hasImage = true;
								iconImageBase64 = getImgBase64FromUrl(imgEles.get(0).attr("src"));
								imgEles.get(0).remove();
							}
						}
						
						Elements treeEle = td.select("a.F1");
						if(treeEle.size() > 0) {
							targetCharacters = treeEle.get(0).html();
							treeEle.get(0).remove();
						}

						Elements cNameEle = td.select("span.Y3");
						if(cNameEle.size() > 0) {
							characterName = cNameEle.get(0).html();
							cNameEle.get(0).remove();
						}
						
						//dice処理
						Elements diceEles = td.select("span.D6");
						for(Element dice : diceEles) {
							String diceHtmlStr = dice.html();
							//dice　Webフォント対応
							diceHtmlStr = diceHtmlStr.replace("<span class=\"R4\">1</span>", "<i class=\"dice dice-1\">&nbsp;</i>");
							diceHtmlStr = diceHtmlStr.replace("2", "<i class=\"dice dice-2\">&nbsp;</i>");
							diceHtmlStr = diceHtmlStr.replace("3", "<i class=\"dice dice-3\">&nbsp;</i>");
							diceHtmlStr = diceHtmlStr.replace("4", "<i class=\"dice dice-4\">&nbsp;</i>");
							diceHtmlStr = diceHtmlStr.replace("5", "<i class=\"dice dice-5\">&nbsp;</i>");
							diceHtmlStr = diceHtmlStr.replace("6", "<i class=\"dice dice-6\">&nbsp;</i>");
							
							dice.after(diceHtmlStr);
							dice.remove();
						}
						
						comment = td.html();
						// 最初に出てくる<br>タグと空リンクを削除する
						comment = comment.replaceFirst("<br>", "");
						//<a href=\"k/now/r1516.html\" target=\"_blank\"></a>
						comment = comment.replaceFirst("(<a href=\"k/now/)[a-z]?[0-9]+(.html\" target=\"_blank\"></a>)", "");
					}
				}else if("BOTTOM".equals(tr.attr("valign"))) {
					Elements tdList = tr.select("td");
					if(tdList.size() > 0) {
						postTime = tdList.get(0).ownText();
						Elements placeSpan = tdList.get(0).select("span.B1");
						if(placeSpan.size() > 0) {
							placeName = placeSpan.get(0).ownText();
						}
					}
				}
			}
			//＊ひとまず画像があればOKとする！
			if(hasImage) {
				IbaraLog newLog = new IbaraLog(characterName, targetCharacters, iconImageBase64, postTime, placeName, comment);
				result.add(newLog);
			}
		}
		
		return result;
	}
	
	/**
	 * 対象ドキュメントが秘密のプレイスであるかどうかを判定します。
	 * @param document
	 * @return
	 * @throws SecretLogException 秘密のプレイスである場合に発生するエラー
	 */
	public Boolean isSecretLog(Document document){
		
		Elements element = document.body().select(".MXM");
		for(Element ele : element) {
			Elements sLabel1 = ele.select(".L4");
			if(!sLabel1.isEmpty() && "！　合言葉の必要なプレイスです　！".equals(sLabel1.get(0).ownText())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 対象のURLから画像データを取得し、Base64文字列に変換する
	 * @param url 画像URL（とりあえずPNG固定。他も使えたっけ？？？）
	 * @return Base64文字列
	 */
	private String getImgBase64FromUrl(String url)  {
		
		if(url == null || url.isEmpty() || !url.startsWith("http")) {
			return "";
		}
		
		//Jsoupを利用して画像を取得
		ByteArrayOutputStream baos = null;
		BufferedOutputStream bos = null;
		String base64Image = "";
		
		try {
			byte[] result = Jsoup
			        .connect(url)
			        .ignoreContentType(true)
			        .execute()
			        .bodyAsBytes();
			if(result != null) {
				BufferedImage image = ImageIO.read(new ByteArrayInputStream(result));
				
				if(image != null) {
					baos = new ByteArrayOutputStream();
					bos = new BufferedOutputStream(baos);
					
					String extension = url.substring(url.lastIndexOf(".") + 1);
					
					ImageIO.write(image, extension, bos);
					bos.flush();
			        bos.close();
			        byte[] bImage = baos.toByteArray();
			        
			        //＊バイト配列→BASE64へ変換する
			        byte[] encoded = Base64.getEncoder().encode(bImage);
			        base64Image = new String(encoded);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return base64Image;
	}
	
}
