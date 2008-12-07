package com.imanager.user.dao;

import com.imanager.user.domain.User;

public interface IUserDao {
	
	/**
	 * ����LoginId����û���Ϣ
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
	 * ���һ���û�
	 * @param user
	 */
	public void insertUser(User user);
	
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

}
