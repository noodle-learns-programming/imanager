package com.imanager.contact.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.DateUtil;
import com.imanager.contact.dao.IContactItemDao;
import com.imanager.contact.dao.IContactTypeDao;
import com.imanager.contact.domain.ContactItem;
import com.imanager.contact.domain.ContactType;
import com.opensymphony.xwork.ActionSupport;

public class ContactItemAction extends ActionSupport {
	
private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ContactItemAction.class);
	
	private IContactItemDao contactItemDao;
	private IContactTypeDao contactTypeDao;
	private String currentLoginId;
	private String contactItemId;
	
	private ContactItem contactItem = new ContactItem();
	private List<ContactItem> contactItemList;
	private List<ContactType> contactTypeList;
	
	
	/**
	 * 初始化添加联系人类型
	 * @return
	 * @throws Exception
	 */
	public String initAddContactItem() throws Exception {
		//TODO currentLoginId = new LoginUtil().getCurrentLogin();
		currentLoginId = "yangqiang";
		
		contactItem.setBirthday(new Date());
		contactItem.setLoginId(currentLoginId);
		
		try{
			contactTypeList = contactTypeDao.getContactTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", initAddContactItem()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "initAddContactItem";
	}
	
	/**
	 * 添加联系人类型
	 * @return
	 * @throws Exception
	 */
	public String addContactItem() throws Exception {
		//TODO currentLoginId = new LoginUtil().getCurrentLogin();
		currentLoginId = "yangqiang";
		
		try{
			contactItem.setCreator(currentLoginId);
			contactItem.setModifier(currentLoginId);
			
			contactItemDao.insertContactItem(contactItem);
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", addContactItem()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "addContactItem";
	}
	
	/**
	 * 根据LoginId获得联系类型列表
	 * @return
	 * @throws Exception
	 */
	public String getContactTypeListByLoginId() throws Exception {
		//TODO currentLoginId = new LoginUtil().getCurrentLogin();
		currentLoginId = "yangqiang";
		
		try{
			contactTypeList = contactTypeDao.getContactTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", getContactTypeListByLoginId()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "getContactTypeListByLoginId";
	}
	
	/**
	 * 根据ID获得一个联系类型
	 * @return
	 * @throws Exception
	 */
	public String getContactTypeById() throws Exception {
		//TODO currentLoginId = new LoginUtil().getCurrentLogin();
		currentLoginId = "yangqiang";
		
		try{
			contactType = contactTypeDao.getContactTypeById(contactTypeId);
			}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", getContactTypeById()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "getContactTypeById";
	}
	
	/**
	 * 更新一个联系类型
	 * @return
	 * @throws Exception
	 */
	public String updateContactType() throws Exception {
		//TODO currentLoginId = new LoginUtil().getCurrentLogin();
		currentLoginId = "yangqiang";
		
		try{
			contactType.setModifier(currentLoginId);
			
			if(contactTypeDao.updateContactType(contactType)){
				return "updateContactType";
			}else{
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", updateContactType()");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String logicDeleteContactType() throws Exception {
		//TODO currentLoginId = new LoginUtil().getCurrentLogin();
		currentLoginId = "yangqiang";
		
		try{
			contactType.setModifier(currentLoginId);
			
			if(contactTypeDao.logicDeleteContactType(contactTypeId, currentLoginId)){
				return "logicDeleteContactType";
			}else{
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", updateContactType()");
			e.printStackTrace();
			return ERROR;
		}
	}


	public String getContactItemId() {
		return contactItemId;
	}

	public void setContactItemId(String contactItemId) {
		this.contactItemId = contactItemId;
	}

	public ContactItem getContactItem() {
		return contactItem;
	}

	public void setContactItem(ContactItem contactItem) {
		this.contactItem = contactItem;
	}

	public List<ContactItem> getContactItemList() {
		return contactItemList;
	}

	public void setContactItemList(List<ContactItem> contactItemList) {
		this.contactItemList = contactItemList;
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

	public List<ContactType> getContactTypeList() {
		return contactTypeList;
	}

	public void setContactTypeList(List<ContactType> contactTypeList) {
		this.contactTypeList = contactTypeList;
	}


}
