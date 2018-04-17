package com.xiaobo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobo.bean.SysUserShiro;
import com.xiaobo.repository.SysUserShiroRepository;
import com.xiaobo.service.SysUserShiroService;

@Service
public class SysUserShiroServiceImpl implements SysUserShiroService{

	@Autowired
	SysUserShiroRepository sysUserShiroRepository;
	
	@Override
	public SysUserShiro findByUsername(String username) {
		List<SysUserShiro> result = sysUserShiroRepository.findByUsername(username);
		if(result!=null&&result.size()>0){
			return result.get(0);
		}
		return null;
	}

}
