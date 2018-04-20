package com.xiaobo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobo.bean.SysResource;
import com.xiaobo.repository.SysResourceRepository;
import com.xiaobo.service.SysResourceService;

@Service
public class SysResourceServiceImpl implements SysResourceService{

	@Autowired
	private SysResourceRepository sysResourceRepository;
	
	@Override
	public List<SysResource> findByIds(List<Long> ids) {
		return sysResourceRepository.findAll(ids);
	}
}
