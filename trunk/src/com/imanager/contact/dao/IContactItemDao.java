package com.imanager.contact.dao;

import java.util.List;

import com.imanager.contact.domain.ContactItem;

public interface IContactItemDao {
	
	/**
	 * 添加一个联系人
	 * @param contactItem
	 */
	public void insertContactItem(ContactItem contactItem);
	
	
	/**
	 * 根据LoginId获得联系人列表
	 * @param loginId
	 * @return
	 */
	public List<ContactItem> getContactItemListByLoginId(String loginId);
	
	/**
	 * 根据ID获得一个联系类型
	 * @param contactTypeId
	 * @return
	 */
	public ContactItem getContactItemById(String contactItemId);
	
	/**
	 * 更新一个联系类型
	 * @param contactType
	 * @return
	 */
	public boolean updateContactItem(ContactItem contactItem);
	
	/**
	 * 逻辑删除联系类型
	 * @param contactTypeId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteContactItem(String contactItemId, String currentLoginId);

}
