package com.imanager.contact.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.DateUtil;
import com.imanager.common.LoginUtil;
import com.imanager.contact.dao.IContactItemDao;
import com.imanager.contact.dao.IContactTypeDao;
import com.imanager.contact.domain.ContactItem;
import com.imanager.contact.domain.ContactType;
import com.imanager.contact.domain.input.ContactItemSearchObj;
import com.opensymphony.xwork.ActionSupport;

public class ContactItemAction extends ActionSupport {
	
private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ContactItemAction.class);
	
	private IContactItemDao contactItemDao;
	private IContactTypeDao contactTypeDao;
	private String currentLoginId;
	private String contactItemId;
	
	private ContactItem contactItem = new ContactItem();
	private ContactItemSearchObj contactSearchObj = new ContactItemSearchObj();
	private List<ContactItem> contactItemList;
	private List<ContactType> contactTypeList;
	
	
	/**
	 * 初始化添加联系人详细
	 * @return
	 * @throws Exception
	 */
	public String initAddContactItem() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
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
	 * 添加联系人详细
	 * @return
	 * @throws Exception
	 */
	public String addContactItem() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		String nameTrim = contactItem.getName().trim();
		String pinyinTrim = contactItem.getPinyin().trim();
		
		try{
			contactItem.setCreator(currentLoginId);
			contactItem.setModifier(currentLoginId);
			contactItem.setAge(DateUtil.getQuotAge(contactItem.getBirthday()));
			contactItem.setName(nameTrim);
			contactItem.setPinyin(pinyinTrim);
			
			contactItemDao.insertContactItem(contactItem);
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", addContactItem()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "addContactItem";
	}
	
	/**
	 * 初始化获得联系人详细列表
	 * @return
	 * @throws Exception
	 */
	public String initGetContactItemListBySearch() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			contactSearchObj.setLoginId(currentLoginId);
			contactTypeList = contactTypeDao.getContactTypeListByLoginId(currentLoginId);
			if (contactTypeList.size() != 0) {
				contactSearchObj.setContactTypeId(String.valueOf(contactTypeList.get(0).getContactTypeId()));
			}
			contactItemList = contactItemDao.getContactItemListBySearch(contactSearchObj);
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", initGetContactItemListBySearch()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "initGetContactItemListBySearch";
	}
	
	/**
	 * 根据查询获得联系人详细列表
	 * @return
	 * @throws Exception
	 */
	public String getContactItemListBySearch() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		String nameTrim = contactSearchObj.getName().trim();
		String pinyinTrim = contactSearchObj.getPinyin().trim();
		contactSearchObj.setName(nameTrim);
		contactSearchObj.setPinyin(pinyinTrim);
		contactSearchObj.setLoginId(currentLoginId);
		
		try{
			contactItemList = contactItemDao.getContactItemListBySearch(contactSearchObj);
			contactTypeList = contactTypeDao.getContactTypeListByLoginId(currentLoginId);
			
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", getContactItemListBySearch()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "getContactItemListBySearch";
	}
	
	/**
	 * 根据ID获得一个联系类型
	 * @return
	 * @throws Exception
	 */
	public String getContactItemById() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			contactTypeList = contactTypeDao.getContactTypeListByLoginId(currentLoginId);
			contactItem = contactItemDao.getContactItemById(contactItemId);
			
			//若联系类型不存在或者被删除，则联系类型置空
			if(contactItem.getContactType().getContactType() == null){
				ContactType contactType = new ContactType();
				contactType.setContactTypeId(0);
				contactType.setContactType("");
				contactItem.setContactType(contactType);
			}
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", getContactItemById()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "getContactItemById";
	}
	
	/**
	 * 更新一个联系类型
	 * @return
	 * @throws Exception
	 */
	public String updateContactItem() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		String nameTrim = contactItem.getName().trim();
		String pinyinTrim = contactItem.getPinyin().trim();
		
		try{
			contactItem.setModifier(currentLoginId);
			contactItem.setAge(DateUtil.getQuotAge(contactItem.getBirthday()));
			contactItem.setName(nameTrim);
			contactItem.setPinyin(pinyinTrim);
			
			if(contactItemDao.updateContactItem(contactItem)){
				return "updateContactItem";
			}else{
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", updateContactItem()");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String logicDeleteContactItem() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			contactItem.setModifier(currentLoginId);
			
			if(contactItemDao.logicDeleteContactItem(contactItemId, currentLoginId)){
				return "logicDeleteContactItem";
			}else{
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + ContactItemAction.class + ", logicDeleteContactItem()");
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

	public ContactItemSearchObj getContactSearchObj() {
		return contactSearchObj;
	}

	public void setContactSearchObj(ContactItemSearchObj contactSearchObj) {
		this.contactSearchObj = contactSearchObj;
	}

}
