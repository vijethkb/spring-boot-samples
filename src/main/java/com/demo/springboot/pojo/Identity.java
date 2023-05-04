package com.demo.springboot.pojo;

public class Identity {

	private Integer id;

	private String name;

	public Identity() {
	}
	
	public Identity(Integer id) {
		this.id = id;
	}
	
	public Identity(String name) {
		this.name = name;
	}
	
	public Identity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
