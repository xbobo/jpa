package com.xiaobo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobo.bean.OneToManySecond;
import com.xiaobo.repository.OneToManySecondRepository;
import com.xiaobo.service.OneToManySecondService;

@Service
public class OneToManySecondServiceImpl implements OneToManySecondService{

	@Autowired
	OneToManySecondRepository oneToManySecondRepository;
	
	@Override
	public List<OneToManySecond> findAll() {
		return oneToManySecondRepository.findAll();
	}

	@Override
	public OneToManySecond findById(Long id) {
		// TODO Auto-generated method stub
		return oneToManySecondRepository.findOne(id);
	}

}
