package com.imanager.contact.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.contact.domain.ContactType;
import com.imanager.contact.service.IContactService;
import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;

public class ContactTypeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ContactTypeAction.class);
	
	// Service
	private IContactService contactService;
	private ILoginService loginService;
	
	// Domain or Var
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
		try{
			currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
			contactType.setLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：初始化添加联系人类型出错！");
			return ERROR;
		}
		
		return "initAddContactType";
	}
	
	/**
	 * 添加联系人类型
	 * @return
	 * @throws Exception
	 */
	public String addContactType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
			String contactTypeTrim = contactType.getContactType().trim();
		
			contactType.setCreator(currentLoginId);
			contactType.setModifier(currentLoginId);
			contactType.setContactType(contactTypeTrim);
			
			contactService.insertContactType(contactType);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：添加联系人类型出错！");
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
		try{
			currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：获得联系类型列表出错！");
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
		try{
			currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
			contactType = contactService.getContactTypeById(contactTypeId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：查看联系类型详细出错！");
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
		try{
			currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
			String contactTypeTrim = contactType.getContactType().trim();
		
			contactType.setModifier(currentLoginId);
			contactType.setContactType(contactTypeTrim);
			
			if(contactService.updateContactType(contactType)){
				return "updateContactType";
			}else{
				addActionError("系统错误：更新联系类型详细出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：更新联系类型详细出错！");
			return ERROR;
		}
	}
	
	/**
	 * 逻辑删除联系类型详细
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteContactType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
			contactType.setModifier(currentLoginId);
			
			if(contactService.logicDeleteContactType(contactTypeId, currentLoginId)){
				return "logicDeleteContactType";
			}else{
				addActionError("系统错误：删除联系类型详细出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：删除联系类型详细出错！");
			return ERROR;
		}
	}

	
	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
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
