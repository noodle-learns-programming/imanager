package com.imanager.contact.dao;

import java.util.List;

import com.imanager.contact.domain.ContactItem;

public interface IContactItemDao {
	
	/**
	 * ���һ����ϵ��
	 * @param contactItem
	 */
	public void insertContactItem(ContactItem contactItem);
	
	
	/**
	 * ����LoginId�����ϵ���б�
	 * @param loginId
	 * @return
	 */
	public List<ContactItem> getContactItemListByLoginId(String loginId);
	
	/**
	 * ����ID���һ����ϵ����
	 * @param contactTypeId
	 * @return
	 */
	public ContactItem getContactItemById(String contactItemId);
	
	/**
	 * ����һ����ϵ����
	 * @param contactType
	 * @return
	 */
	public boolean updateContactItem(ContactItem contactItem);
	
	/**
	 * �߼�ɾ����ϵ����
	 * @param contactTypeId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteContactItem(String contactItemId, String currentLoginId);

}
