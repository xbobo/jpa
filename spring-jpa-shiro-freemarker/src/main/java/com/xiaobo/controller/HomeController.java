package com.xiaobo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaobo.security.Digests;
import com.xiaobo.security.Encodes;
import com.xiaobo.vo.Msg;

@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }
    
	@RequestMapping(value = "/test.do")
	public String opinionJudgeManagement(Map<String, Object> model,
			Integer pageNo) {
		try {
			return "test";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("管理员---舆情数据研判-查看出错", e);
			return "/";
		}
	}
	
	public static void main(String[] args) {
		byte[] salt=Encodes.decodeHex("4330b05d7ed9d0e8");
		String password="a123456";
		byte[] hashPassword = Digests.sha1(password.getBytes(),salt,Encodes.HASH_INTERATIONS);
		String passwordEncode=Encodes.encodeHex(hashPassword);
		System.out.println(passwordEncode);
	}
}