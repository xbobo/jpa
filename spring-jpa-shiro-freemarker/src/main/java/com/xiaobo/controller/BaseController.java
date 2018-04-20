package com.xiaobo.controller;

import org.apache.shiro.SecurityUtils;

import com.xiaobo.security.ShiroDbRealm.ShiroUser;
/**
 * 
 * @Package: com.xiaobo.controller 
 * @author: xiaobo   
 * @date: 2018年4月18日 下午2:01:24 
 *
 */
public abstract class BaseController {
	
	public ShiroUser getCurrentShiroUser() {
		return (ShiroUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	public String getCurrentUserName() {
		return getCurrentShiroUser().getLoginName();
	}
	
	public AjaxResult success(Object data) {
		return AjaxResult.success(data);
	}
	
	public AjaxResult error(String errorMsg) {
		return AjaxResult.error(errorMsg);
	}

}
