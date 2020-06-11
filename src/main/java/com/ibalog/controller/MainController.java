package com.ibalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ���C��Controller
 * @author ntmk
 */
@Controller
@RequestMapping("/main")
public class MainController {

	/**
	 * ���C����ʂ�\�����܂�
	 * @return
	 */
	@RequestMapping("/")
    public String main() {
        return "main/index";
    }
	
	/**
	 * �ۑ��p�e���v���[�gHTML��Ԃ��܂�
	 * @return
	 */
	@RequestMapping("/save")
    public String save() {
        return "main/save-log-base";
    }
}
