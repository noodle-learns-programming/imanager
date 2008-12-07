package com.imanager.user.exception;

public enum UserServiceErrMsg {
	
	/**
	 * �û����Ѵ���
	 */
	User_name_already_exist("�û����Ѵ��ڣ�"),
	
	/**
	 * ��¼ID�Ѵ���
	 */
	Login_id_already_exist("��¼ID�Ѵ��ڣ�");
	
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
