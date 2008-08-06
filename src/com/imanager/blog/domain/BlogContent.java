package com.imanager.blog.domain;

import java.util.Date;

import com.imanager.common.Basement;

public class BlogContent extends Basement{

	int blogContentId;
	
	String title;
	
	String content;
	
	String fullFilePath;
	
	String fullFileName;
	
	Date blogDate;
	
	String blogType;
	
	int blogItem1Id;
	
	int blogItem2Id;
	
	String weekday;
	
	String weather;
	
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFullFilePath() {
		return fullFilePath;
	}

	public void setFullFilePath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}

	public String getFullFileName() {
		return fullFileName;
	}

	public void setFullFileName(String fullFileName) {
		this.fullFileName = fullFileName;
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

	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}

	public int getBlogItem1Id() {
		return blogItem1Id;
	}

	public void setBlogItem1Id(int blogItem1Id) {
		this.blogItem1Id = blogItem1Id;
	}

	public int getBlogItem2Id() {
		return blogItem2Id;
	}

	public void setBlogItem2Id(int blogItem2Id) {
		this.blogItem2Id = blogItem2Id;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
