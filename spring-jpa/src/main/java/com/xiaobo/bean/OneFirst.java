package com.xiaobo.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OneFirst{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="name_fk")
	private OneSecond second;

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
}
