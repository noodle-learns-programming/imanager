package com.imanager.contact.dao;

import java.util.List;

import com.imanager.contact.domain.ContactType;

/**
 * ��ϵ�����͹���
 * @author Yang Qiang
 * @since 2008-08-03
 */
public interface IContactTypeDao {
	
	/**
	 * ���һ����ϵ������
	 * @param contactType
	 */
	public void insertContactType(ContactType contactType);
	
	/**
	 * ����LoginId�����ϵ�����б�
	 * @param loginId
	 * @return
	 */
	public List<ContactType> getContactTypeListByLoginId(String loginId);
	
	/**
	 * ����ID���һ����ϵ����
	 * @param contactTypeId
	 * @return
	 */
	public ContactType getContactTypeById(String contactTypeId);
	
	/**
	 * ����һ����ϵ����
	 * @param contactType
	 * @return
	 */
	public boolean updateContactType(ContactType contactType);
	
	/**
	 * �߼�ɾ����ϵ����
	 * @param contactTypeId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteContactType(String contactTypeId, String currentLoginId);

}
