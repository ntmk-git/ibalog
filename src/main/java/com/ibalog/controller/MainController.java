package com.ibalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * メインController
 * @author ntmk
 */
@Controller
@RequestMapping("/main")
public class MainController {

	/**
	 * メイン画面を表示します
	 * @return
	 */
	@RequestMapping("/")
    public String main() {
        return "main/index";
    }
	
	/**
	 * 保存用テンプレートHTMLを返します
	 * @return
	 */
	@RequestMapping("/save")
    public String save() {
        return "main/save-log-base";
    }
}
