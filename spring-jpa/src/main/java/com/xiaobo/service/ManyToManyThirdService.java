package com.xiaobo.service;

import java.util.List;

import com.xiaobo.bean.ManyToManyThird;

public interface ManyToManyThirdService {

	List<ManyToManyThird> findAll();
	
	ManyToManyThird findById(Long id);
}
