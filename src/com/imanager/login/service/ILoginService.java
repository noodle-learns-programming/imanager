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
	 * ���뵱ǰ�û�����¼��ǰ��¼�û���longId
	 * @param loginId
	 * @return
	 */
	public boolean loginCurrentUser(String loginId);
	
	/**
	 * �ǳ���ǰ�û�
	 * @param loginid
	 * @return
	 */
	public boolean logoutCurrentUser();

}
