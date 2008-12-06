package com.imanager.contact.service.impl;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.imanager.contact.dao.IContactItemDao;
import com.imanager.contact.dao.IContactTypeDao;
import com.imanager.contact.domain.ContactItem;
import com.imanager.contact.domain.ContactType;
import com.imanager.contact.domain.input.ContactItemSearchObj;
import com.imanager.contact.service.IContactService;

public class ContactServiceImpl implements IContactService {
	
	private TransactionTemplate transactionTemplate;
	private IContactItemDao contactItemDao;
	private IContactTypeDao contactTypeDao;
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#insertContactItem(com.imanager.contact.domain.ContactItem)
	 */
	public void insertContactItem(ContactItem contactItem) {
		contactItemDao.insertContactItem(contactItem);
	}

	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#getContactItemListBySearch(com.imanager.contact.domain.input.ContactItemSearchObj)
	 */
	@SuppressWarnings("unchecked")
	public List<ContactItem> getContactItemListBySearch(ContactItemSearchObj contactSearchObj) {
		return contactItemDao.getContactItemListBySearch(contactSearchObj);
	}

	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#getContactItemById(java.lang.String)
	 */
	public ContactItem getContactItemById(String contactItemId) {
		return contactItemDao.getContactItemById(contactItemId);
	}

	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#updateContactItem(com.imanager.contact.domain.ContactItem)
	 */
	public boolean updateContactItem(ContactItem contactItem) {
		return contactItemDao.updateContactItem(contactItem);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#logicDeleteContactItem(java.lang.String, java.lang.String)
	 */
	public boolean logicDeleteContactItem(String contactItemId, String currentLoginId) {
		return contactItemDao.logicDeleteContactItem(contactItemId, currentLoginId);
	}

	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#insertContactType(com.imanager.contact.domain.ContactType)
	 */
	public void insertContactType(ContactType contactType){
		contactTypeDao.insertContactType(contactType);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#getContactTypeListByLoginId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ContactType> getContactTypeListByLoginId(String loginId){
		return contactTypeDao.getContactTypeListByLoginId(loginId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#getContactTypeById(java.lang.String)
	 */
	public ContactType getContactTypeById(String contactTypeId){
		return contactTypeDao.getContactTypeById(contactTypeId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#updateContactType(com.imanager.contact.domain.ContactType)
	 */
	public boolean updateContactType(ContactType contactType){
		return contactTypeDao.updateContactType(contactType);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.service.IContactService#logicDeleteContactType(java.lang.String, java.lang.String)
	 */
	public boolean logicDeleteContactType(String contactTypeId, String currentLoginId){
		return contactTypeDao.logicDeleteContactType(contactTypeId, currentLoginId);
	}
	
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public IContactItemDao getContactItemDao() {
		return contactItemDao;
	}
	public void setContactItemDao(IContactItemDao contactItemDao) {
		this.contactItemDao = contactItemDao;
	}
	public IContactTypeDao getContactTypeDao() {
		return contactTypeDao;
	}
	public void setContactTypeDao(IContactTypeDao contactTypeDao) {
		this.contactTypeDao = contactTypeDao;
	}

}
