package com.xiaobo.service;

import java.util.List;

import com.xiaobo.bean.OneToManySecond;

public interface OneToManySecondService {

	List<OneToManySecond> findAll();
	
	OneToManySecond findById(Long id);
}
