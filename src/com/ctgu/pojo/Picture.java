package com.ctgu.pojo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Picture implements Serializable{
	private Integer id;
	private String picName;
	private Integer userId;
	
	public Picture() {
		super();
	}
	public Picture(Integer id, String picName, Integer userId) {
		super();
		this.id = id;
		this.picName = picName;
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String toString() {
		return "Picture [id=" + id + ", picName=" + picName + ", userId=" + userId + "]";
	}
	
}
