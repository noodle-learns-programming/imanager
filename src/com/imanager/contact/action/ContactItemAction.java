package com.imanager.contact.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;

import com.imanager.common.DateUtil;
import com.imanager.contact.domain.ContactItem;
import com.imanager.contact.domain.ContactType;
import com.imanager.contact.domain.input.ContactItemSearchObj;
import com.imanager.contact.service.IContactService;
import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;
import com.imanager.util.FileUtils;

public class ContactItemAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ContactItemAction.class);
	private static final String CONTACT_PHOTO = "contact/photo";
	
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
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	
	
	

	/**
	 * ��ʼ�������ϵ����ϸ
	 * @return
	 * @throws Exception
	 */
	public String initAddContactItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			contactItem.setBirthday(new Date());
			contactItem.setLoginId(currentLoginId);
		
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺳�ʼ�������ϵ����ϸ����");
			return ERROR;
		}
		
		return "initAddContactItem";
	}
	
	/**
	 * �����ϵ����ϸ
	 * @return
	 * @throws Exception
	 */
	public String addContactItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String srcDir = env.get(EnvService.SRC_DIR).toString();
			String nameTrim = contactItem.getName().trim();
			String pinyinTrim = contactItem.getPinyin().trim();
		
			contactItem.setCreator(currentLoginId);
			contactItem.setModifier(currentLoginId);
			contactItem.setAge(DateUtil.getQuotAge(contactItem.getBirthday()));
			contactItem.setName(nameTrim);
			contactItem.setPinyin(pinyinTrim);
			
			if (picture != null) {
				StringBuffer absFileName = new StringBuffer();
				absFileName.append(srcDir).append("/").
					append(loginService.getCurrentLoginId()).append("/").
					append(CONTACT_PHOTO).append("/").
					append(pinyinTrim).
					append(FileUtils.getSuffixOfFile(pictureFileName));
				FileCopyUtils.copy(picture, new File(absFileName.toString()));
				
				StringBuffer fileNamePath = new StringBuffer();
				fileNamePath.append("/").
					append(loginService.getCurrentLoginId()).append("/").
					append(CONTACT_PHOTO).append("/").
					append(pinyinTrim).
					append(FileUtils.getSuffixOfFile(pictureFileName));
				contactItem.setPhoto(fileNamePath.toString());
			}
			
			log.info("shit:--------- " + pictureFileName);
			log.error("shit:--------- " + pictureContentType);
			
			contactService.insertContactItem(contactItem);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���������ϵ����ϸ����");
			return ERROR;
		}
		
		return "addContactItem";
	}
	
	/**
	 * ��ʼ�������ϵ����ϸ�б�
	 * @return
	 * @throws Exception
	 */
	public String initGetContactItemList() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			contactSearchObj.setLoginId(currentLoginId);
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
			if (contactTypeList.size() != 0) {
				contactSearchObj.setContactTypeId(String.valueOf(contactTypeList.get(0).getContactTypeId()));
			}
			contactItemList = contactService.getContactItemListBySearch(contactSearchObj);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺻����ϵ����ϸ�б����");
			return ERROR;
		}
		
		return "initGetContactItemList";
	}
	
	/**
	 * ���ݲ�ѯ�����ϵ����ϸ�б�
	 * @return
	 * @throws Exception
	 */
	public String getContactItemListBySearch() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			String nameTrim = contactSearchObj.getName().trim();
			String pinyinTrim = contactSearchObj.getPinyin().trim();
			contactSearchObj.setName(nameTrim);
			contactSearchObj.setPinyin(pinyinTrim);
			contactSearchObj.setLoginId(currentLoginId);
		
			contactItemList = contactService.getContactItemListBySearch(contactSearchObj);
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺲�ѯ��ϵ����ϸ�б����");
			return ERROR;
		}
		
		return "getContactItemListBySearch";
	}
	
	/**
	 * �鿴��ϵ����ϸ
	 * @return
	 * @throws Exception
	 */
	public String getContactItemById() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
			contactItem = contactService.getContactItemById(contactItemId);
			
			//����ϵ���Ͳ����ڻ��߱�ɾ��������ϵ�����ÿ�
			if(contactItem.getContactType().getContactType() == null){
				ContactType contactType = new ContactType();
				contactType.setContactTypeId(0);
				contactType.setContactType("");
				contactItem.setContactType(contactType);
			}
			StringBuffer fileNamePath = new StringBuffer(env.get(EnvService.SRC_URL).toString());
			fileNamePath.append(contactItem.getPhoto());
			contactItem.setPhoto(fileNamePath.toString());
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺲�ѯ��ϵ����ϸ����");
			return ERROR;
		}
		
		return "getContactItemById";
	}
	
	/**
	 * ������ϵ����ϸ
	 * @return
	 * @throws Exception
	 */
	public String updateContactItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String srcDir = env.get(EnvService.SRC_DIR).toString();
			String nameTrim = contactItem.getName().trim();
			String pinyinTrim = contactItem.getPinyin().trim();
		
			contactItem.setModifier(currentLoginId);
			contactItem.setAge(DateUtil.getQuotAge(contactItem.getBirthday()));
			contactItem.setName(nameTrim);
			contactItem.setPinyin(pinyinTrim);
			
			if (picture != null) {
				StringBuffer absFileName = new StringBuffer();
				absFileName.append(srcDir).append("/").
					append(loginService.getCurrentLoginId()).append("/").
					append(CONTACT_PHOTO).append("/").
					append(pinyinTrim).
					append(FileUtils.getSuffixOfFile(pictureFileName));
				FileCopyUtils.copy(picture, new File(absFileName.toString()));
				contactItem.setPhoto(absFileName.toString());
			}
			
			if(contactService.updateContactItem(contactItem)){
				return "updateContactItem";
			}else{
				addActionError("ϵͳ���󣺸�����ϵ����ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺸�����ϵ����ϸ����");
			return ERROR;
		}
	}
	
	/**
	 * �߼�ɾ����ϵ����ϸ
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteContactItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			contactItem.setModifier(currentLoginId);
			
			if(contactService.logicDeleteContactItem(contactItemId, currentLoginId)){
				return "logicDeleteContactItem";
			}else{
				addActionError("ϵͳ����ɾ����ϵ����ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ����ɾ����ϵ����ϸ����");
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

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

}
