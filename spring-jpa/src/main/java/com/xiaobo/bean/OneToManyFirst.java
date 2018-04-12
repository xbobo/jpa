package com.xiaobo.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OneToManyFirst {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false,length=50)
	private String name;
	
	@OneToMany(mappedBy="first")
	private List<OneToManySecond> lists;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OneToManySecond> getLists() {
		return lists;
	}

	public void setLists(List<OneToManySecond> lists) {
		this.lists = lists;
	}
}
