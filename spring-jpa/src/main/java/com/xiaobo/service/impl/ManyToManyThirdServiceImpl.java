package com.xiaobo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobo.bean.ManyToManyThird;
import com.xiaobo.repository.ManyToManyThirdRepository;
import com.xiaobo.service.ManyToManyThirdService;
@Service
public class ManyToManyThirdServiceImpl  implements ManyToManyThirdService{

	@Autowired
	ManyToManyThirdRepository manyToManyThirdRepository;
	
	@Override
	public List<ManyToManyThird> findAll() {
		return manyToManyThirdRepository.findAll();
	}

	@Override
	public ManyToManyThird findById(Long id) {
		// TODO Auto-generated method stub
		return manyToManyThirdRepository.findOne(id);
	}

}
