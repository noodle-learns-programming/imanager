package com.imanager.user.service;

import com.imanager.user.domain.User;
import com.imanager.user.exception.UserServiceException;

public interface IUserService {
	/**
	 * ����loginId����û���Ϣ
	 * @param loginId
	 * @return �����ڷ���User�������ڷ���null
	 */
	public User getUserByLoginIdNPassword(String loginId, String password);
	
	/**
	 * ����userId����û���Ϣ
	 * @param userId
	 * @return
	 */
	public User getUserByUserId(String userId);
	
	/**
	 * ����loginId����û���Ϣ
	 * @param loginId
	 * @return
	 */
	public User getUserByLoginId(String loginId);
	
	/**
	 * ע��һ���û�
	 * @param user
	 * @return
	 * @throws UserServiceException
	 */
	public boolean registerUser(User user) throws UserServiceException;
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean updateUserInfo(User user);
	
	/**
	 * �����û�����
	 * @param user
	 * @return
	 */
	public boolean updateUserPassword(User user);
	
	/**
	 * ������Ҫ���ļ���
	 * @param folders
	 * @return
	 */
	public boolean createFolders(String srcDir, String currentLoginId, String folders);
}
