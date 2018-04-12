package com.xiaobo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobo.bean.OneFirst;
import com.xiaobo.repository.OneFirstRepository;
import com.xiaobo.service.OneFirstService;

@Service
public class OneFirstServiceImpl  implements OneFirstService{

	@Autowired
	OneFirstRepository oneFirstRepository;
	
	@Override
	public List<OneFirst> findAll() {
		return oneFirstRepository.findAll();
	}

}
