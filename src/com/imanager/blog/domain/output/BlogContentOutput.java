package com.imanager.blog.domain.output;

import java.util.Date;

import com.imanager.common.Basement;

public class BlogContentOutput extends Basement{

	int blogContentId;
	
	String title;
	
	Date blogDate;
	
	String blogType;
	
	String item1Chn;
	
	String item2Chn;
	
	String loginId;

	public int getBlogContentId() {
		return blogContentId;
	}

	public void setBlogContentId(int blogContentId) {
		this.blogContentId = blogContentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}

	public String getBlogType() {
		return blogType;
	}

	public String getItem1Chn() {
		return item1Chn;
	}

	public void setItem1Chn(String item1Chn) {
		this.item1Chn = item1Chn;
	}

	public String getItem2Chn() {
		return item2Chn;
	}

	public void setItem2Chn(String item2Chn) {
		this.item2Chn = item2Chn;
	}

	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
