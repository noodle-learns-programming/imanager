package com.imanager.login.service;

public interface ILoginService {
	
	/**
	 * �����ϴε�¼ʱ��
	 * @param loginId
	 * @return
	 */
	public boolean updateLastLoginDate(String loginId);

}
