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
	public String getCurrentLoginId(String recordType);
	
	/**
	 * ��¼��ǰ��¼�û���longId
	 * @param recordType
	 * @return
	 */
	public boolean recordCurrentLoginId(String recordType, String loginId);

}
