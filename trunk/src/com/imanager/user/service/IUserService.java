package com.imanager.user.service;

import com.imanager.user.domain.User;
import com.imanager.user.exception.UserServiceException;

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
	
	/**
	 * 注册一个用户
	 * @param user
	 * @return
	 * @throws UserServiceException
	 */
	public boolean registerUser(User user) throws UserServiceException;
	
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
	
	/**
	 * 创建需要的文件夹
	 * @param folders
	 * @return
	 */
	public boolean createFolders(String srcDir, String currentLoginId, String folders);
}
