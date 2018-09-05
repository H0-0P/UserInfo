package com.ctgu.utils;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PageBean<T> implements Serializable{
	private int currentPage = 1; //��ǰҳ
	private int pageCount = 5;   //ÿҳ��ʾ������
	private int totalCount;      //������
	private int totalPage;	     //��ҳ��
	private List<T> pageData;	 //��ҳ��ѯ��������
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
	//������ҳ��
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
