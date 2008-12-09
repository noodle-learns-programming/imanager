package com.imanager.login.service;

public interface ILoginService {
	
	/**
	 * �����ϴε�¼ʱ��
	 * @param loginId
	 * @return
	 */
	public boolean updateLastLoginDate(String loginId);
	
	
	/**
	 * ��õ�ǰ��¼�û���longId
	 * @return
	 */
	public String getCurrentLoginId();
	
	
	/**
	 * ��¼��ǰ��¼�û���longId
	 * @param loginId
	 * @return
	 */
	public boolean recordCurrentLoginId(String loginId);

}
