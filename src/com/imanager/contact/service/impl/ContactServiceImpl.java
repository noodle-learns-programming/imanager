package com.imanager.contact.service.impl;

import org.springframework.transaction.support.TransactionTemplate;

import com.imanager.contact.dao.IContactItemDao;
import com.imanager.contact.dao.IContactTypeDao;
import com.imanager.contact.service.IContactService;

public class ContactServiceImpl implements IContactService {
	
	private TransactionTemplate transactionTemplate;
	private IContactItemDao contactItemDao;
	private IContactTypeDao contactTypeDao;
	
	
	
	
	
	
	
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
