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

import com.ibalog.api.dto.IbaraLog;

/**
 * jsoup doc to IbaraLog List
 * @author ntmk
 */
public class DocumentConverter {
	
	/**
	 * ���O�C�����Ă����ʂ��ǂ����𔻒肷��
	 * ���ЂƂ܂��@<td class="F5B">Jump</td> �����݂��Ă��Ȃ����Ƃ������Ƃ���
	 * @param document
	 * @return ���O�C�����FTrue�^�����O�C���FFalse
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
	 * Document��IbaraLog�֕ϊ�����
	 * @param document
	 * @return
	 */
	public List<IbaraLog> docToIbaraLogList(Document document){
		List<IbaraLog> result = new ArrayList<IbaraLog>();
		
		Elements tableList = document.body().select("table.SE0");
		for(Element tbl : tableList) {
			Elements trList = tbl.select("tr");
			
			for(Element tr : trList) {
				Boolean hasImage = false;
				String iconImageBase64 = "";
				String characterName = "";
				String targetCharacters = "";
				String comment = "";
				
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
					
					comment = td.html();
				}
				
				//���ЂƂ܂��摜�������OK�Ƃ���I
				if(hasImage) {
					IbaraLog newLog = new IbaraLog(characterName, targetCharacters, iconImageBase64, comment);
					result.add(newLog);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * �Ώۂ�URL����摜�f�[�^���擾���ABase64������ɕϊ�����
	 * @param url �摜URL�i�Ƃ肠����PNG�Œ�B�����g���������H�H�H�j
	 * @return Base64������
	 */
	private String getImgBase64FromUrl(String url)  {
		
		if(url == null || url.isEmpty() || !url.startsWith("http")) {
			return "";
		}
		
		//Jsoup�𗘗p���ĉ摜���擾
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
			        
			        //���o�C�g�z��BASE64�֕ϊ�����
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
