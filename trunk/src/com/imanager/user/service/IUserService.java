package com.imanager.user.service;

import com.imanager.user.domain.User;

public interface IUserService {
	/**
	 * 根据loginId获得用户信息
	 * @param loginId
	 * @return 若存在返回User，不存在返回null
	 */
	public User getUserByLoginIdNPassword(String loginId, String password);
	
	/**
	 * 根据userId获得用户信息
	 * @param userId
	 * @return
	 */
	public User getUserByUserId(String userId);
	
	/**
	 * 根据loginId获得用户信息
	 * @param loginId
	 * @return
	 */
	public User getUserByLoginId(String loginId);
}
