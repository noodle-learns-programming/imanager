package com.imanager.login.service;

public interface ILoginService {
	
	/**
	 * 更新上次登录时间
	 * @param loginId
	 * @return
	 */
	public boolean updateLastLoginDate(String loginId);

}
