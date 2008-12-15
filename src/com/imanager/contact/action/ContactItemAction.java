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
	 * 初始化添加联系人详细
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
			addActionError("系统错误：初始化添加联系人详细出错！");
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
			addActionError("系统错误：添加联系人详细出错！");
			return ERROR;
		}
		
		return "addContactItem";
	}
	
	/**
	 * 初始化获得联系人详细列表
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
			addActionError("系统错误：获得联系人详细列表出错！");
			return ERROR;
		}
		
		return "initGetContactItemList";
	}
	
	/**
	 * 根据查询获得联系人详细列表
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
			addActionError("系统错误：查询联系人详细列表出错！");
			return ERROR;
		}
		
		return "getContactItemListBySearch";
	}
	
	/**
	 * 查看联系人详细
	 * @return
	 * @throws Exception
	 */
	public String getContactItemById() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			contactTypeList = contactService.getContactTypeListByLoginId(currentLoginId);
			contactItem = contactService.getContactItemById(contactItemId);
			
			//若联系类型不存在或者被删除，则联系类型置空
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
			addActionError("系统错误：查询联系人详细出错！");
			return ERROR;
		}
		
		return "getContactItemById";
	}
	
	/**
	 * 更新联系人详细
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
				addActionError("系统错误：更新联系人详细出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：更新联系人详细出错！");
			return ERROR;
		}
	}
	
	/**
	 * 逻辑删除联系人详细
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
				addActionError("系统错误：删除联系人详细出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：删除联系人详细出错！");
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
