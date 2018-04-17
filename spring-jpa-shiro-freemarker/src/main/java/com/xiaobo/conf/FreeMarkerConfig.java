package com.xiaobo.conf;

import static freemarker.template.Configuration.DEFAULT_ENCODING_KEY;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/**
 * 
 * @author xiaobo
 *
 */
@Configuration
public class FreeMarkerConfig {
	@Autowired
	private freemarker.template.Configuration configuration;
	
	@PostConstruct
	public void setSharedVariable(){
		try {
			configuration.setSetting(DEFAULT_ENCODING_KEY, "UTF-8");
			configuration.setSharedVariable("emotion", new EmotionDirective());
			configuration.setSharedVariable("shiro",new ShiroTags());
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
	}
}
