package com.xiaobo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaobo.bean.SysUserShiro;

public interface  SysUserShiroRepository extends JpaRepository<SysUserShiro, Long>{
	
	List<SysUserShiro> findByUsername(String username);
}
