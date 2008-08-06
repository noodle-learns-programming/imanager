package com.imanager.login.dao;

import com.imanager.login.domain.User;

public interface ILoginDao {
	
	/**
	 * ����LoginId����û���Ϣ
	 * @param loginId
	 * @return �����ڷ���User�������ڷ���null
	 */
	public User getUserByLoginId(String loginId, String password);
	
	/**
	 * �����ϴε�¼ʱ��
	 * @param loginId
	 * @return
	 */
	public boolean updateLastLoginDate(String loginId);
	
}
