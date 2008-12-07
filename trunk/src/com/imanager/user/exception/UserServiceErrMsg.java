package com.imanager.user.exception;

public enum UserServiceErrMsg {
	
	/**
	 * 用户名已存在
	 */
	User_name_already_exist("用户名已存在！"),
	
	/**
	 * 登录ID已存在
	 */
	Login_id_already_exist("登录ID已存在！");
	
	private final String description;
	
	UserServiceErrMsg(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
