package com.xiaobo.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaobo.security.ShiroDbRealm.ShiroUser;
import com.xiaobo.service.SysUserShiroService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private SysUserShiroService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

    	ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user", userService.findByUsername(shiroUser.getLoginName()));
        return true;
    }
}
