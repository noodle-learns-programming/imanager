package com.imanager.login.dao;

public interface ILoginDao {
	
	/**
	 * 更新上次登录时间
	 * @param loginId
	 * @return
	 */
	public boolean updateLastLoginDate(String loginId);
	
}
