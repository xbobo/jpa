package com.xiaobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaobo.bean.ManyToManyThird;

public interface ManyToManyThirdRepository extends JpaRepository<ManyToManyThird, Long> {

}
