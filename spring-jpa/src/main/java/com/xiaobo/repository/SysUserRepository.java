package com.xiaobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaobo.bean.SysUser;

public interface  SysUserRepository extends JpaRepository<SysUser, Long>{
	
	SysUser findByUsername(String username);
}
