package com.xiaobo.controller;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaobo.util.RSAUtils;

/**
 * 
 * @Package: com.xiaobo.controller 
 * @author: xiaobo   
 * @date: 2018年4月18日 下午2:01:16 
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		generateKey(request);
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/logOut")
	public String logOut(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		return "redirect:/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(HttpServletRequest request,
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {

		String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String msg = null;

		if (error != null) {
			if (error.equals(IncorrectCredentialsException.class.getName())) {
				msg = "用户名或密码错误";
			}
		}
		model.addAttribute("errorMsg", msg);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		generateKey(request);
		return "login";
	}

	// 生成随机码
	private void generateKey(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			map = RSAUtils.getKeys();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// ignore
		}
		if (map != null) {
			// 生成公钥和私钥
			RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
			RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
			// 私钥保存在session中，用于解密
			request.getSession().setAttribute("privateKey", privateKey);

			// 公钥信息保存在页面，用于加密
			String publicKeyExponent = publicKey.getPublicExponent().toString(16);
			String publicKeyModulus = publicKey.getModulus().toString(16);
			request.setAttribute("publicKeyExponent", publicKeyExponent);
			request.setAttribute("publicKeyModulus", publicKeyModulus);
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {

		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("admin")) {
			System.out.println("admin");
			return "test";
		} else if (subject.hasRole("public")) {
			System.out.println("public");
			return "test";
		}
		
		return "redirect:/login";
	}
	
}
