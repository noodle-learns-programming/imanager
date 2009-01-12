package com.imanager.tools.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;

import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;
import com.imanager.tools.domain.EmbedTools;
import com.imanager.tools.service.IEmbedToolsService;
import com.imanager.util.FileUtils;

public class ToolsAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ToolsAction.class);
	
	// Serivce
	private IEmbedToolsService embedToolsService;
	private ILoginService loginService;
	
	// Domain or Var
	private EmbedTools embedTools = new EmbedTools();
	private List<EmbedTools> embedList = new ArrayList<EmbedTools>();
	private String embedToolsId;
	private String currentLoginId;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private String embedToolsType;

	public String weather() throws Exception {
		return "weather";
	}
	
	public String calendar() throws Exception {
		return "calendar";
	}
	
	public String googleMap() throws Exception {
		return "googlemap";
	}
	
	/**
	 * ���Ƕ�빤���б�
	 * @return
	 * @throws Exception
	 */
	public String getEmbedToolsList() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			embedList = embedToolsService.getEmbedToolsList(currentLoginId);
			return "getEmbedToolsList";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺻��Ƕ�빤���б����");
			return ERROR;
		}
	}
	
	/**
	 * �������ͻ��Ƕ�빤���б�
	 * @return
	 * @throws Exception
	 */
	public String getEmbedToolsListByType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			embedList = embedToolsService.getEmbedToolsListByType(currentLoginId, embedToolsType);
			return "getEmbedToolsListByType";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺻��Ƕ�빤���б����");
			return ERROR;
		}
	}
	
	/**
	 * չ��һ��Ƕ�빤��
	 * @return
	 * @throws Exception
	 */
	public String showEmbedTools() throws Exception {
		try{
			embedTools = embedToolsService.getEmbedToolsById(embedToolsId);
			return "showEmbedTools";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺻��Ƕ�빤����ϸ����");
			return ERROR;
		}
	}
	
	/**
	 * ��ʼ�����Ƕ�빤��
	 * @return
	 * @throws Exception
	 */
	public String initAddEmbedTools() throws Exception {
		try{
			embedTools.setLoginId(loginService.getCurrentLoginId());
			return "initAddEmbedTools";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺳�ʼ�����Ƕ�빤�߳���");
			return ERROR;
		}
	}
	
	/**
	 * ���Ƕ�빤��
	 * @return
	 * @throws Exception
	 */
	public String addEmbedTools() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String srcDir = env.get(EnvService.SRC_DIR).toString();
			embedTools.setLoginId(currentLoginId);
			embedTools.setCreator(currentLoginId);
			embedTools.setModifier(currentLoginId);
			embedTools.setToolsName(embedTools.getToolsName().trim());
			embedTools.setToolsDescription(embedTools.getToolsDescription().trim());
			embedTools.setToolsScript(embedTools.getToolsScript().trim());
			
			if (picture != null) {
				String checkFolderResult = checkPhotoFolder(srcDir, currentLoginId);
				if (!"has".equals(checkFolderResult)) {
					addActionError(checkFolderResult);
					return ERROR;
				}
				
				//���û����洢·��
				StringBuffer baseFilePath = new StringBuffer();
				baseFilePath.append("/").
					append(loginService.getCurrentLoginId()).
					append("/tools/embed/").
					append(System.currentTimeMillis()).
					append(FileUtils.getSuffixOfFile(pictureFileName));
				
				//��ͼƬ���浽�̶��ļ�����
				String absFilePath = srcDir + baseFilePath.toString();
				FileCopyUtils.copy(picture, new File(absFilePath));
				embedTools.setToolsPicPath(baseFilePath.toString());
			}
			
			embedToolsService.insertEmbedTools(embedTools);
			
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ�������Ƕ�빤�߳���");
			return ERROR;
		}
		
		return "addEmbedTools";
	}
	
	/**
	 * ���Ƕ�빤����ϸ
	 * @return
	 * @throws Exception
	 */
	public String getEmbedToolsById() throws Exception {
		try{
			embedTools = embedToolsService.getEmbedToolsById(embedToolsId);
			return "getEmbedToolsById";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺻��Ƕ�빤����ϸ����");
			return ERROR;
		}
	}
	
	/**
	 * ����Ƕ�빤��
	 * @return
	 * @throws Exception
	 */
	public String updateEmbedTools() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String srcDir = env.get(EnvService.SRC_DIR).toString();
			embedTools.setModifier(currentLoginId);
			embedTools.setToolsName(embedTools.getToolsName().trim());
			embedTools.setToolsDescription(embedTools.getToolsDescription().trim());
			embedTools.setToolsScript(embedTools.getToolsScript().trim());
			
			if (picture != null) {
				String checkFolderResult = checkPhotoFolder(srcDir, currentLoginId);
				if (!"has".equals(checkFolderResult)) {
					addActionError(checkFolderResult);
					return ERROR;
				}
				
				//���û����洢·��
				StringBuffer baseFilePath = new StringBuffer();
				baseFilePath.append("/").
					append(loginService.getCurrentLoginId()).
					append("/tools/embed/").
					append(System.currentTimeMillis()).
					append(FileUtils.getSuffixOfFile(pictureFileName));
				
				//��ͼƬ���浽�̶��ļ�����
				String absFilePath = srcDir + baseFilePath.toString();
				FileCopyUtils.copy(picture, new File(absFilePath));
				embedTools.setToolsPicPath(baseFilePath.toString());
			}
			
			if (embedToolsService.updateEmbedTools(embedTools)) {
				return "updateEmbedTools";
			} else {
				addActionError("ϵͳ���󣺸���Ƕ�빤�߳���");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺸���Ƕ�빤�߳���");
			return ERROR;
		}
	}
	
	/**
	 * �߼�ɾ��һ��Ƕ�빤��
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteEmbedTools() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			embedTools.setModifier(currentLoginId);
			
			if(embedToolsService.logicDeleteEmbedToolsById(embedToolsId, currentLoginId)){
				return "logicDeleteEmbedTools";
			}else{
				addActionError("ϵͳ����ɾ��Ƕ�빤�߳���");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ����ɾ��Ƕ�빤�߳���");
			return ERROR;
		}
	}
	
	/**
	 * ����ļ����Ƿ񴴽�
	 * @param srcDir
	 * @param currentLoginId
	 * @return
	 */
	private String checkPhotoFolder(String srcDir, String currentLoginId) {
		File file = new File(srcDir + "/" + currentLoginId);
		if (!file.exists() && !file.mkdir()) {	//��¼�û��ļ���δ�����򴴽�ʧ��
			return "ϵͳ���󣺵�¼�û��ļ��в����ڣ�";
		}
		
		file = new File(srcDir + "/" + currentLoginId + "/tools");
		if (!file.exists() && !file.mkdir()) {	//�����ļ���δ�����򴴽�ʧ��
			return "ϵͳ���󣺹����ļ��в����ڣ�";
		}
		
		file = new File(srcDir + "/" + currentLoginId + "/tools/embed");
		if (file.exists() || (!file.exists() && file.mkdir())) {	//Ƕ�빤���ļ���δ�����򴴽�ʧ��
			return "has";
		} else {
			return "ϵͳ����Ƕ�빤���ļ��в����ڣ�";
		}
	}
	
	

	public IEmbedToolsService getEmbedToolsService() {
		return embedToolsService;
	}

	public void setEmbedToolsService(IEmbedToolsService embedToolsService) {
		this.embedToolsService = embedToolsService;
	}

	public EmbedTools getEmbedTools() {
		return embedTools;
	}

	public void setEmbedTools(EmbedTools embedTools) {
		this.embedTools = embedTools;
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

	public List<EmbedTools> getEmbedList() {
		return embedList;
	}

	public void setEmbedList(List<EmbedTools> embedList) {
		this.embedList = embedList;
	}

	public String getEmbedToolsId() {
		return embedToolsId;
	}

	public void setEmbedToolsId(String embedToolsId) {
		this.embedToolsId = embedToolsId;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public String getEmbedToolsType() {
		return embedToolsType;
	}

	public void setEmbedToolsType(String embedToolsType) {
		this.embedToolsType = embedToolsType;
	}

}
