package com.imanager.login.dao;

import com.imanager.login.domain.User;

public interface ILoginDao {
	
	/**
	 * 根据LoginId获得用户信息
	 * @param loginId
	 * @return 若存在返回User，不存在返回null
	 */
	public User getUserByLoginId(String loginId, String password);
	
	/**
	 * 更新上次登录时间
	 * @param loginId
	 * @return
	 */
	public boolean updateLastLoginDate(String loginId);
	
}
