package com.imanager.blog.domain;

import com.imanager.common.Basement;

public class BlogItem1 extends Basement{

	private int blogItem1Id;
	
	private String itemChn;
	
	private String itemEng;
	
	private String item2Count;
	
	private String loginId;

	public int getBlogItem1Id() {
		return blogItem1Id;
	}

	public void setBlogItem1Id(int blogItem1Id) {
		this.blogItem1Id = blogItem1Id;
	}

	public String getItemChn() {
		return itemChn;
	}

	public void setItemChn(String itemChn) {
		this.itemChn = itemChn;
	}

	public String getItemEng() {
		return itemEng;
	}

	public void setItemEng(String itemEng) {
		this.itemEng = itemEng;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getItem2Count() {
		return item2Count;
	}

	public void setItem2Count(String item2Count) {
		this.item2Count = item2Count;
	}
}
