package com.imanager.contact.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.LoginUtil;
import com.imanager.contact.dao.IContactTypeDao;
import com.imanager.contact.domain.ContactType;
import com.opensymphony.xwork.ActionSupport;

public class ContactTypeAction extends ActionSupport {
	
private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ContactTypeAction.class);
	
	private IContactTypeDao contactTypeDao;
	private String currentLoginId;
	private String contactTypeId;
	
	private ContactType contactType = new ContactType();
	private List<ContactType> contactTypeList;
	
	
	/**
	 * 初始化添加联系人类型
	 * @return
	 * @throws Exception
	 */
	public String initAddContactType() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		contactType.setLoginId(currentLoginId);
		
		return "initAddContactType";
	}
	
	/**
	 * 添加联系人类型
	 * @return
	 * @throws Exception
	 */
	public String addContactType() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		String contactTypeTrim = contactType.getContactType().trim();
		
		try{
			contactType.setCreator(currentLoginId);
			contactType.setModifier(currentLoginId);
			contactType.setContactType(contactTypeTrim);
			
			contactTypeDao.insertContactType(contactType);
		}catch (Exception e){
			log.error("Error: " + ContactTypeAction.class + ", addContactType()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "addContactType";
	}
	
	/**
	 * 根据LoginId获得联系类型列表
	 * @return
	 * @throws Exception
	 */
	public String getContactTypeListByLoginId() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			contactTypeList = contactTypeDao.getContactTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error("Error: " + ContactTypeAction.class + ", getContactTypeListByLoginId()");
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
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			contactType = contactTypeDao.getContactTypeById(contactTypeId);
			}catch (Exception e){
			log.error("Error: " + ContactTypeAction.class + ", getContactTypeById()");
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
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		String contactTypeTrim = contactType.getContactType().trim();
		
		try{
			contactType.setModifier(currentLoginId);
			contactType.setContactType(contactTypeTrim);
			
			if(contactTypeDao.updateContactType(contactType)){
				return "updateContactType";
			}else{
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + ContactTypeAction.class + ", updateContactType()");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String logicDeleteContactType() throws Exception {
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			contactType.setModifier(currentLoginId);
			
			if(contactTypeDao.logicDeleteContactType(contactTypeId, currentLoginId)){
				return "logicDeleteContactType";
			}else{
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + ContactTypeAction.class + ", updateContactType()");
			e.printStackTrace();
			return ERROR;
		}
	}

	
	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
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

	public String getContactTypeId() {
		return contactTypeId;
	}

	public void setContactTypeId(String contactTypeId) {
		this.contactTypeId = contactTypeId;
	}

}
