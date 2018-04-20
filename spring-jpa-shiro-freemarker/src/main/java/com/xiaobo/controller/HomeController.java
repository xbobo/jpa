package com.xiaobo.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaobo.security.Digests;
import com.xiaobo.security.Encodes;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequiresPermissions("organization:create")
    @RequestMapping("/index")
    public String index(Model model) {
        return "comlogin";
    }
	
	@RequiresPermissions("user:create")
	@RequestMapping(value = "/test.do")
	public String test(Map<String, Object> model,
			Integer pageNo) {
		try {
			return "test";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("管理员---舆情数据研判-查看出错", e);
			return "/";
		}
	}
	
	@RequestMapping(value = "/first.do")
	public String first(Map<String, Object> model,
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