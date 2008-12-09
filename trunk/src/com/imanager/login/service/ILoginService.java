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
	public String getCurrentLoginId();
	
	
	/**
	 * 登入当前用户，记录当前登录用户的longId
	 * @param loginId
	 * @return
	 */
	public boolean loginCurrentUser(String loginId);
	
	/**
	 * 登出当前用户
	 * @param loginid
	 * @return
	 */
	public boolean logoutCurrentUser();

}
