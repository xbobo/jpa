package com.xiaobo.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class OneFirst{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="name_fk")
	private OneSecond second;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OneSecond getSecond() {
		return second;
	}

	public void setSecond(OneSecond second) {
		this.second = second;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
