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

}
