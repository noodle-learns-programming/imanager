package com.imanager.contact.dao;

import java.util.List;

import com.imanager.contact.domain.ContactType;

/**
 * 联系人类型管理
 * @author Yang Qiang
 * @since 2008-08-03
 */
public interface IContactTypeDao {
	
	/**
	 * 添加一个联系人类型
	 * @param contactType
	 */
	public void insertContactType(ContactType contactType);
	
	/**
	 * 根据LoginId获得联系类型列表
	 * @param loginId
	 * @return
	 */
	public List<ContactType> getContactTypeListByLoginId(String loginId);
	
	/**
	 * 根据ID获得一个联系类型
	 * @param contactTypeId
	 * @return
	 */
	public ContactType getContactTypeById(String contactTypeId);
	
	/**
	 * 更新一个联系类型
	 * @param contactType
	 * @return
	 */
	public boolean updateContactType(ContactType contactType);
	
	/**
	 * 逻辑删除联系类型
	 * @param contactTypeId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteContactType(String contactTypeId, String currentLoginId);

}
