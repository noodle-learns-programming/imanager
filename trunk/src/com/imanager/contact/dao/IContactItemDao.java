package com.imanager.contact.dao;

import java.util.List;

import com.imanager.contact.domain.ContactItem;
import com.imanager.contact.domain.input.ContactItemSearchObj;

public interface IContactItemDao {
	
	/**
	 * ���һ����ϵ��
	 * @param contactItem
	 */
	public void insertContactItem(ContactItem contactItem);
	
	
	/**
	 * ���ݲ���������LoginId�����ϵ���б�
	 * @param loginId
	 * @return
	 */
	public List<ContactItem> getContactItemListBySearch(ContactItemSearchObj contactSearchObj);
	
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
