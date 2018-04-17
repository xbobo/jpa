package com.xiaobo.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xiaobo.security.MyFormAuthenticationFilter;
import com.xiaobo.security.ShiroDbRealm;

@Configuration
public class ShiroConfiguration {
	
	private final static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
	private final static Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
	
	@Bean(name = "shiroDbRealm")
	public ShiroDbRealm getShiroDbRealm() {
		return new ShiroDbRealm();
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(getShiroDbRealm());
		return dwsm;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/login.do", "authc");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/lib/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		filters.put("authc", getMyFormAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(filters);
		
		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setUnauthorizedUrl("/login.do");
		shiroFilterFactoryBean.setLoginUrl("/login.do");
		shiroFilterFactoryBean.setSuccessUrl("/login/index.do");
		
		return shiroFilterFactoryBean;
	}
	
	@Bean(name = "loginFormAuthenticationFilter")
	public MyFormAuthenticationFilter getMyFormAuthenticationFilter() {
		return new MyFormAuthenticationFilter();
	}

}
