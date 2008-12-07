package com.imanager.user.dao;

import com.imanager.user.domain.User;

public interface IUserDao {
	
	/**
	 * 根据LoginId获得用户信息
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
	
	/**
	 * 添加一个用户
	 * @param user
	 */
	public void insertUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUserInfo(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public boolean updateUserPassword(User user);

}
