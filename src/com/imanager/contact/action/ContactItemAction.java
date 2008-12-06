package com.imanager.contact.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.DateUtil;
import com.imanager.contact.domain.ContactItem;
import com.imanager.contact.domain.ContactType;
import com.imanager.contact.domain.input.ContactItemSearchObj;
import com.imanager.contact.service.IContactService;
import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;

public class ContactItemAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ContactItemAction.class);
	
	// Service
	private IContactService contactService;
	private ILoginService loginService;
	
	// Domain or Var
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
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		contactItem.setBirthday(new Date());
		contactItem.setLoginId(currentLoginId);
		
		try{
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
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
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String nameTrim = contactItem.getName().trim();
		String pinyinTrim = contactItem.getPinyin().trim();
		
		try{
			contactItem.setCreator(currentLoginId);
			contactItem.setModifier(currentLoginId);
			contactItem.setAge(DateUtil.getQuotAge(contactItem.getBirthday()));
			contactItem.setName(nameTrim);
			contactItem.setPinyin(pinyinTrim);
			
			contactService.insertContactItem(contactItem);
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
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			contactSearchObj.setLoginId(currentLoginId);
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
			if (contactTypeList.size() != 0) {
				contactSearchObj.setContactTypeId(String.valueOf(contactTypeList.get(0).getContactTypeId()));
			}
			contactItemList = contactService.getContactItemListBySearch(contactSearchObj);
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
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String nameTrim = contactSearchObj.getName().trim();
		String pinyinTrim = contactSearchObj.getPinyin().trim();
		contactSearchObj.setName(nameTrim);
		contactSearchObj.setPinyin(pinyinTrim);
		contactSearchObj.setLoginId(currentLoginId);
		
		try{
			contactItemList = contactService.getContactItemListBySearch(contactSearchObj);
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
			
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
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
			contactItem = contactService.getContactItemById(contactItemId);
			
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
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String nameTrim = contactItem.getName().trim();
		String pinyinTrim = contactItem.getPinyin().trim();
		
		try{
			contactItem.setModifier(currentLoginId);
			contactItem.setAge(DateUtil.getQuotAge(contactItem.getBirthday()));
			contactItem.setName(nameTrim);
			contactItem.setPinyin(pinyinTrim);
			
			if(contactService.updateContactItem(contactItem)){
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
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			contactItem.setModifier(currentLoginId);
			
			if(contactService.logicDeleteContactItem(contactItemId, currentLoginId)){
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

	public IContactService getContactService() {
		return contactService;
	}

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

}
