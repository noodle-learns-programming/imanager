package com.imanager.contact.domain.input;

public class ContactItemSearchObj {
	
	private String name;
	private String pinyin;
	private String contactTypeId;
	private String loginId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getContactTypeId() {
		return contactTypeId;
	}
	public void setContactTypeId(String contactTypeId) {
		this.contactTypeId = contactTypeId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


}
