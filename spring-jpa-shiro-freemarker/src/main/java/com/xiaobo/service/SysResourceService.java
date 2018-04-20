package com.xiaobo.service;

import java.util.List;

import com.xiaobo.bean.SysResource;

public interface SysResourceService {

	public List<SysResource> findByIds(List<Long> ids);
}
