package com.xiaobo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobo.bean.OneToManyFirst;
import com.xiaobo.repository.OneToManyFirstRepository;
import com.xiaobo.service.OneToManyFirstService;

@Service
public class OneToManyFirstServiceImpl implements OneToManyFirstService {

	@Autowired
	private OneToManyFirstRepository oneToManyFirstRepository;
	
	@Override
	public List<OneToManyFirst> findAll() {
		return oneToManyFirstRepository.findAll();
	}

}
