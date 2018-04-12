package com.xiaobo.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ManyToManyThird {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false,length=50)
	private String thirdName;
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
	@JoinTable(name="seconds_thirds",
		joinColumns={@JoinColumn(name="third_id",referencedColumnName="id"), },
		inverseJoinColumns={@JoinColumn(name="second_id",referencedColumnName="id")})
	private Set<OneToManySecond> seconds=new HashSet<OneToManySecond>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public Set<OneToManySecond> getSeconds() {
		return seconds;
	}

	@JsonBackReference
	public void setSeconds(Set<OneToManySecond> seconds) {
		this.seconds = seconds;
	}
}
