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
	 * 获得嵌入工具列表
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
			addActionError("系统错误：获得嵌入工具列表出错！");
			return ERROR;
		}
	}
	
	/**
	 * 根据类型获得嵌入工具列表
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
			addActionError("系统错误：获得嵌入工具列表出错！");
			return ERROR;
		}
	}
	
	/**
	 * 展现一个嵌入工具
	 * @return
	 * @throws Exception
	 */
	public String showEmbedTools() throws Exception {
		try{
			embedTools = embedToolsService.getEmbedToolsById(embedToolsId);
			return "showEmbedTools";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：获得嵌入工具详细出错！");
			return ERROR;
		}
	}
	
	/**
	 * 初始化添加嵌入工具
	 * @return
	 * @throws Exception
	 */
	public String initAddEmbedTools() throws Exception {
		try{
			embedTools.setLoginId(loginService.getCurrentLoginId());
			return "initAddEmbedTools";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：初始化添加嵌入工具出错！");
			return ERROR;
		}
	}
	
	/**
	 * 添加嵌入工具
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
				
				//设置基本存储路径
				StringBuffer baseFilePath = new StringBuffer();
				baseFilePath.append("/").
					append(loginService.getCurrentLoginId()).
					append("/tools/embed/").
					append(System.currentTimeMillis()).
					append(FileUtils.getSuffixOfFile(pictureFileName));
				
				//将图片保存到固定文件夹下
				String absFilePath = srcDir + baseFilePath.toString();
				FileCopyUtils.copy(picture, new File(absFilePath));
				embedTools.setToolsPicPath(baseFilePath.toString());
			}
			
			embedToolsService.insertEmbedTools(embedTools);
			
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：添加嵌入工具出错！");
			return ERROR;
		}
		
		return "addEmbedTools";
	}
	
	/**
	 * 获得嵌入工具详细
	 * @return
	 * @throws Exception
	 */
	public String getEmbedToolsById() throws Exception {
		try{
			embedTools = embedToolsService.getEmbedToolsById(embedToolsId);
			return "getEmbedToolsById";
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：获得嵌入工具详细出错！");
			return ERROR;
		}
	}
	
	/**
	 * 更新嵌入工具
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
				
				//设置基本存储路径
				StringBuffer baseFilePath = new StringBuffer();
				baseFilePath.append("/").
					append(loginService.getCurrentLoginId()).
					append("/tools/embed/").
					append(System.currentTimeMillis()).
					append(FileUtils.getSuffixOfFile(pictureFileName));
				
				//将图片保存到固定文件夹下
				String absFilePath = srcDir + baseFilePath.toString();
				FileCopyUtils.copy(picture, new File(absFilePath));
				embedTools.setToolsPicPath(baseFilePath.toString());
			}
			
			if (embedToolsService.updateEmbedTools(embedTools)) {
				return "updateEmbedTools";
			} else {
				addActionError("系统错误：更新嵌入工具出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：更新嵌入工具出错！");
			return ERROR;
		}
	}
	
	/**
	 * 逻辑删除一个嵌入工具
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
				addActionError("系统错误：删除嵌入工具出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：删除嵌入工具出错！");
			return ERROR;
		}
	}
	
	/**
	 * 检查文件夹是否创建
	 * @param srcDir
	 * @param currentLoginId
	 * @return
	 */
	private String checkPhotoFolder(String srcDir, String currentLoginId) {
		File file = new File(srcDir + "/" + currentLoginId);
		if (!file.exists() && !file.mkdir()) {	//登录用户文件夹未创建或创建失败
			return "系统错误：登录用户文件夹不存在！";
		}
		
		file = new File(srcDir + "/" + currentLoginId + "/tools");
		if (!file.exists() && !file.mkdir()) {	//工具文件夹未创建或创建失败
			return "系统错误：工具文件夹不存在！";
		}
		
		file = new File(srcDir + "/" + currentLoginId + "/tools/embed");
		if (file.exists() || (!file.exists() && file.mkdir())) {	//嵌入工具文件夹未创建或创建失败
			return "has";
		} else {
			return "系统错误：嵌入工具文件夹不存在！";
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
