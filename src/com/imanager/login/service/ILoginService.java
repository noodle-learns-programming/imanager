package com.imanager.login.service;

public interface ILoginService {
	
	/**
	 * 更新上次登录时间
	 * @param loginId
	 * @return
	 */
	public boolean updateLastLoginDate(String loginId);
	
	/**
	 * 获得当前登录用户的longId
	 * @return
	 */
	public String getCurrentLoginId(String recordType);
	
	/**
	 * 记录当前登录用户的longId
	 * @param recordType
	 * @return
	 */
	public boolean recordCurrentLoginId(String recordType, String loginId);

}
