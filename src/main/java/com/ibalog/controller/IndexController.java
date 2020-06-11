package com.ibalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ROOT
 * @author ntmk
 */
@Controller
@RequestMapping("/")
public class IndexController {

	/**
	 * main画面へリダイレクト
	 * @return
	 */
	@RequestMapping("/")
    public String main() {
        return "redirect:/main/";
    }
	
}
