package com.ctgu.utils;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PageBean<T> implements Serializable{
	private int currentPage = 1; //当前页
	private int pageCount = 5;   //每页显示多少行
	private int totalCount;      //总行数
	private int totalPage;	     //总页数
	private List<T> pageData;	 //分页查询到的数据
	public PageBean() {
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	//返回总页数
	public int getTotalPage() {
		if(totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		} else {
			totalPage = totalCount / pageCount + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
}
