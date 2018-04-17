package com.xiaobo.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OneToManySecond {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional=false)
	private OneToManyFirst first;
	
	@ManyToMany(mappedBy="seconds",fetch=FetchType.EAGER)
	private Set<ManyToManyThird> thirds=new HashSet<ManyToManyThird>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OneToManyFirst getFirst() {
		return first;
	}

	@JsonBackReference
	public void setFirst(OneToManyFirst first) {
		this.first = first;
	}

	public Set<ManyToManyThird> getThirds() {
		return thirds;
	}

	public void setThirds(Set<ManyToManyThird> thirds) {
		this.thirds = thirds;
	}
}
