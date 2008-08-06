package com.imanager.blog.domain.input;

import java.util.Date;

public class BlogContentSearchObj {

	String title;		//可选 支持模糊查询
	
	Date startBlogDate;	//必选 默认当月第一天
	
	Date engBlogDate;	//必选 默认当月最后一天
	
	String blogType;	//可选
	
	String blogItem1Id;	//可选
	
	String blogItem2Id;	//可选
	
	String loginId;		//必选 默认当前登录用户

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartBlogDate() {
		return startBlogDate;
	}

	public void setStartBlogDate(Date startBlogDate) {
		this.startBlogDate = startBlogDate;
	}

	public Date getEngBlogDate() {
		return engBlogDate;
	}

	public void setEngBlogDate(Date engBlogDate) {
		this.engBlogDate = engBlogDate;
	}

	public String getBlogType() {
		return blogType;
	}

	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}

	public String getBlogItem1Id() {
		return blogItem1Id;
	}

	public void setBlogItem1Id(String blogItem1Id) {
		this.blogItem1Id = blogItem1Id;
	}

	public String getBlogItem2Id() {
		return blogItem2Id;
	}

	public void setBlogItem2Id(String blogItem2Id) {
		this.blogItem2Id = blogItem2Id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
